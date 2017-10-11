package com.lt.entity;

import java.sql.Date;

/**
 * 用户
 * Created by LT on 2017/8/2.
 */
public class BUser {

    private String userId;
    /**
     * 真是姓名
     */
    private String userName;
    private String mobilePhone;
    private String password;
    private String email;
    /**
     * 系统登录用户名
     */
    private String sysUserName;
    /**
     * 注册时间
     */
    private Date registTime;
    /**
     * 最后一次修改密码时间
     */
    private Date lastResetPasswordTime;

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLastResetPasswordTime() {
        return lastResetPasswordTime;
    }

    public void setLastResetPasswordTime(Date lastResetPasswordTime) {
        this.lastResetPasswordTime = lastResetPasswordTime;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
