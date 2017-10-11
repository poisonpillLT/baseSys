package com.lt.service;

import com.lt.entity.BUser;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by LT on 2017/8/2.
 */
public interface IUserService {

    /**
     * 添加用户
     */
    int addUser(BUser user);

    /**
     * 批量删除用户
     */
    int deleteUser(String[] ids);

    /**
     * 修改用户信息
     */
    int modUser(BUser user);

    /**
     * 查询用户信息
     */
    List<BUser> queryUser(Map<String, Object> params);

    /**
     * 根据系统用户名得到用户信息
     *
     * @param userName 用户名（登录用户名）
     * @return 用户信息
     */
    BUser getUserByUserName(String userName);


}
