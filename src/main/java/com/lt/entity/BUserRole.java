package com.lt.entity;

/**
 * 用户---角色关系
 * Created by LT on 2017/8/2.
 */
public class BUserRole {

    private String userId;

    private String roleId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
