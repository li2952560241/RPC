package com.TWTW.example.consumer;

import com.TWTW.rpc.config.RpcConfig;
import com.TWTW.rpc.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 *
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
