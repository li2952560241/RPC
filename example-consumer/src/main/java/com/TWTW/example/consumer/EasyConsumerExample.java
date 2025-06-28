package com.TWTW.example.consumer;

import com.TWTW.example.common.model.User;
import com.TWTW.example.common.service.UserService;

/**
 * @author TWTW
 * @version 1.0
 * 描述：简易服务消费者示例
 * @date 2025/6/28 上午11:29
 */

public class EasyConsumerExample {

    public static void main(String[] args) {
        // todo 需要获取 UserService 的实现类对象
        UserService userService = null;
        User user = new User();
        user.setName("yupi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}