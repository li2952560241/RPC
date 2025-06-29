package com.TWTW.rpc.server;

import io.vertx.core.Vertx;

/**
 * @author TWTW
 * @version 1.0
 * 描述：用vertx实现http服务
 * @date 2025/6/28 上午11:34
 */

public class VertxHttpServer implements HttpServer {

    /**
     * 启动服务器
     *
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}
