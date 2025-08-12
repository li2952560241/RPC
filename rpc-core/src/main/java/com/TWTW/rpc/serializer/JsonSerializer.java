package com.TWTW.rpc.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * JSON序列化器实现类
 * 使用Jackson库进行JSON的序列化与反序列化
 */
public class JsonSerializer implements Serializer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        try {
            // 将对象序列化为JSON字节数组
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new IOException("JSON序列化失败", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        try {
            // 将JSON字节数组反序列化为指定类型的对象
            return objectMapper.readValue(bytes, type);
        } catch (JsonProcessingException e) {
            throw new IOException("JSON反序列化失败", e);
        }
    }
}
