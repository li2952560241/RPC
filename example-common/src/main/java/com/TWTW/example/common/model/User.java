package com.TWTW.example.common.model;

import java.io.Serializable;

/**
 * @author TWTW
 * @version 1.0
 * 描述：公共模块的User 类 用来进行消息的传递所以需要序列化
 * @date 2025/6/28 上午11:17
 */

public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
