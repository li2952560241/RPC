package com.TWTW.example.provider;

import com.TWTW.example.common.model.User;
import com.TWTW.example.common.service.UserService;

/**
 * @author TWTW
 * @version 1.0
 * 描述：用户服务实现类
 * @date 2025/6/28 上午11:24
 */
public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
