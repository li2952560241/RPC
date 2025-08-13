package com.TWTW.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author TWTW
 * @version 1.0
 * 描述：服务代理工厂（用于创建代理对象）
 * @date 2025/6/28 下午1:27
 */

public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
//        if (RpcApplication.getRpcConfig().isMock()) {
//            return getMockProxy(serviceClass);
//        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxyHttp());
    }

    /**
     * 根据服务类获取 Mock 代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}
