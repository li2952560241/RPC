package com.TWTW.example.provider;


import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.RpcApplication;
import com.TWTW.rpc.registry.LocalRegistry;
import com.TWTW.rpc.server.HttpServer;
import com.TWTW.rpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
