package com.TWTW.example.consumer;

import com.TWTW.example.common.model.User;
import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.proxy.ServiceProxyFactory;

/**
 * @author TWTW
 * @version 1.0
 * 描述：简易服务消费者示例
 * @date 2025/6/28 上午11:29
 */

public class EasyConsumerExample {

    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("TWTW");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}