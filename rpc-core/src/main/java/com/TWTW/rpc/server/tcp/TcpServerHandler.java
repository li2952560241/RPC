package com.TWTW.rpc.server.tcp;

import com.TWTW.rpc.model.RpcRequest;
import com.TWTW.rpc.model.RpcResponse;
import com.TWTW.rpc.protocol.ProtocolMessage;
import com.TWTW.rpc.protocol.ProtocolMessageDecoder;
import com.TWTW.rpc.protocol.ProtocolMessageEncoder;
import com.TWTW.rpc.protocol.ProtocolMessageTypeEnum;
import com.TWTW.rpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;

public class TcpServerHandler implements Handler<NetSocket> {


    /**
     * 处理请求
     *
     * @param socket the event to handle
     */
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 处理请求代码
        });
        socket.handler(bufferHandlerWrapper);
    }
}
