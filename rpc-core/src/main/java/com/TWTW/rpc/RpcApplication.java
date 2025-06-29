package com.TWTW.rpc;

import com.TWTW.rpc.config.RpcConfig;
import com.TWTW.rpc.constant.RpcConstant;
import com.TWTW.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TWTW
 * @version 1.0
 * 描述：RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 * @date 2025/6/29 下午12:32
 */

@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
