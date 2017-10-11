package com.lt.service;

import com.lt.entity.BUser;
import com.lt.tools.results.Results;

/**
 * Created by LT on 2017/8/9.
 */
public interface IAuthService {

    /**
     * 注册
     *
     * @param userToAdd 注册用户信息
     * @return 注册后的用户
     */
    Results register(BUser userToAdd);

    /**
     * 登录
     *
     * @param username 登录名
     * @param password 密码
     * @return 结果
     */
    Results login(String username, String password);

    /**
     * 刷新token
     *
     * @param oldToken 之前的token
     * @return 新token
     */
    String refresh(String oldToken);

}
