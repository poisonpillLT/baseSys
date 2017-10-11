package com.lt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 * Created by LT on 2017/8/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "user";
    }

}
