package com.lt.service.impl;

import com.lt.dao.UserDao;
import com.lt.entity.BUser;
import com.lt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户业务层
 * Created by LT on 2017/8/2.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     */
    @Override
    public int addUser(BUser user) {
        int result;
        user.setUserId(UUID.randomUUID().toString());
        result = userDao.add(user);
        return result;
    }

    /**
     * 批量删除用户
     */
    @Override
    public int deleteUser(String[] ids) {
        int result;
        result = userDao.delete(ids);
        return result;
    }

    /**
     * 修改用户信息
     */
    @Override
    public int modUser(BUser user) {
        int result;
        result = userDao.update(user);
        return result;
    }

    /**
     * 查询用户信息
     */
    @Override
    public List<BUser> queryUser(Map<String, Object> params) {
        List<BUser> users = userDao.query(params);
        return users;
    }

    @Override
    public BUser getUserByUserName(String userName) {
        Map<String, Object> params = new HashMap<>();
        params.put("sysUserName", userName);
        List<BUser> users = userDao.query(params);
        return users == null ? null : users.get(0);
    }
}
