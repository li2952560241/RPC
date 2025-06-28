package com.TWTW.rpc.serializer;

import java.io.IOException;

/**
 * @author TWTW
 * @version 1.0
 * 描述：序列化器接口
 * @date 2025/6/28 下午1:03
 */

public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}