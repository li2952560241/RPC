package com.TWTW.example.provider;


import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.RpcApplication;
import com.TWTW.rpc.config.RegistryConfig;
import com.TWTW.rpc.config.RpcConfig;
import com.TWTW.rpc.model.ServiceMetaInfo;
import com.TWTW.rpc.registry.LocalRegistry;
import com.TWTW.rpc.registry.Registry;
import com.TWTW.rpc.registry.RegistryFactory;
import com.TWTW.rpc.server.HttpServer;
import com.TWTW.rpc.server.VertxHttpServer;


/**
 * 服务提供者示例
 *
 */
public class ProviderExampleHttp {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}

