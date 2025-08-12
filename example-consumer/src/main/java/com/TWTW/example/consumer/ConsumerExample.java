package com.TWTW.example.consumer;

import com.TWTW.example.common.model.User;
import com.TWTW.example.common.service.UserService;
import com.TWTW.rpc.config.RpcConfig;
import com.TWTW.rpc.proxy.ServiceProxyFactory;
import com.TWTW.rpc.utils.ConfigUtils;

/**
 * 服务消费者示例
 *
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
