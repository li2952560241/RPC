package com.TWTW.rpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.TWTW.rpc.RpcApplication;
import com.TWTW.rpc.config.RpcConfig;
import com.TWTW.rpc.constant.RpcConstant;
import com.TWTW.rpc.fault.retry.RetryStrategy;
import com.TWTW.rpc.fault.retry.RetryStrategyFactory;
import com.TWTW.rpc.loadbalancer.LoadBalancer;
import com.TWTW.rpc.loadbalancer.LoadBalancerFactory;
import com.TWTW.rpc.model.RpcRequest;
import com.TWTW.rpc.model.RpcResponse;
import com.TWTW.rpc.model.ServiceMetaInfo;
import com.TWTW.rpc.registry.Registry;
import com.TWTW.rpc.registry.RegistryFactory;
import com.TWTW.rpc.serializer.Serializer;
import com.TWTW.rpc.serializer.SerializerFactory;
import com.TWTW.rpc.server.tcp.VertxTcpClient;
;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务代理（JDK 动态代理）
        *
        * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
        * @learn <a href="https://codefather.cn">编程宝典</a>
        * @from <a href="https://yupi.icu">编程导航知识星球</a>
        */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            // rpc 请求
            // 使用重试机制
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            RpcResponse rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
            return rpcResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException("调用失败");
        }
    }
}
