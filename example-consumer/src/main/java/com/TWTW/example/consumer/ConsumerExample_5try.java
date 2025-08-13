package com.TWTW.example.consumer;

import com.TWTW.example.common.model.User;
import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者示例
 *
 */
public class ConsumerExample_5try {

    public static void main(String[] args) throws InterruptedException {
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");
        // 调用
        User newUser = userService.getUser(user);
        System.out.println("第一次调用");
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("第一次调用user == null");
        }

        newUser = userService.getUser(user);
        System.out.println("第二次调用");
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("第二次调用user == null");
        }
        // 暂停30秒
        Thread.sleep(30 * 1000);
        System.out.println("第三次调用");
        newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("第三次调用user == null");
        }
    }
}
