package com.TWTW.rpc.server;

/**
 * @author TWTW
 * @version 1.0
 * 描述：HTTP 服务器接口
 * @date 2025/6/28 上午11:34
 */

public interface HttpServer {

    /**
     * 启动服务器
     *
     * @param port
     */
    void doStart(int port);
}