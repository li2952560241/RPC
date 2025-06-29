package com.TWTW.example.common.service;

import com.TWTW.example.common.model.User;

/**
 * @author TWTW
 * @version 1.0
 * 描述：用户接口类
 * @date 2025/6/28 上午11:19
 */
public interface UserService {
    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user) ;
}
