package com.TWTW.example.provider;

import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.registry.LocalRegistry;
import com.TWTW.rpc.server.HttpServer;
import com.TWTW.rpc.server.VertxHttpServer;

/**
 * @author TWTW
 * @version 1.0
 * 描述：简易服务提供者示例
 * @date 2025/6/28 上午11:26
 */

public class EasyProviderExample {

    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}