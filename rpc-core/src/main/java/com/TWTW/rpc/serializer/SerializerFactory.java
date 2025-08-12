package com.TWTW.rpc.serializer;
import com.TWTW.rpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;
/**
 * @author TWTW
 * @version 1.0
 * 描述：序列化器工厂（用于获取序列化器对象）
 * @date 2025/8/3 下午4:12
 */

public class SerializerFactory {

    /**
     * 使用Spi 加载所有序列化器
     */
    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}

