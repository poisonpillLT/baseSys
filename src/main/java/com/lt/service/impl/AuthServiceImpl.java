package com.lt.service.impl;

import com.lt.dao.UserDao;
import com.lt.entity.BUser;
import com.lt.service.IAuthService;
import com.lt.springSecurity.JWTTokenUtil;
import com.lt.springSecurity.JWTUser;
import com.lt.tools.results.Results;
import com.lt.tools.results.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

/**
 * 用户
 * Created by LT on 2017/8/9.
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Autowired
    private UserDao userDao;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 注册
     *
     * @param userToAdd 注册用户信息
     * @return 注册后的用户
     */
    @Override
    public Results register(BUser userToAdd) {
        if (userDao.queryBySysUserName(userToAdd.getSysUserName()) != null) {
            return ResultsUtil.getErrorResult("用户名已存在！");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        Date date = new Date(System.currentTimeMillis());
        userToAdd.setLastResetPasswordTime(date);
        userToAdd.setRegistTime(date);
        userToAdd.setUserId(UUID.randomUUID().toString());
        userDao.add(userToAdd);
        return ResultsUtil.getSuccessResult().setData(userToAdd);
    }

    /**
     * 登录
     *
     * @param username 登录名
     * @param password 密码
     * @return 结果
     */
    @Override
    public Results login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            return ResultsUtil.getErrorResult("用户不存在！");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(userDetails.getPassword().equals(encoder.encode(password))){
            return ResultsUtil.getErrorResult("密码错误！");
        }
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResultsUtil.getSuccessResult().setData(token);
    }

    /**
     * 刷新token
     *
     * @param oldToken 之前的token
     * @return 新token
     */
    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JWTUser user = (JWTUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
