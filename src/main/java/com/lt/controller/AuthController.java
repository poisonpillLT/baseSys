package com.lt.controller;

import com.lt.entity.BUser;
import com.lt.service.IAuthService;
import com.lt.springSecurity.JwtAuthenticationRequest;
import com.lt.springSecurity.JwtAuthenticationResponse;
import com.lt.tools.ServletUtil;
import com.lt.tools.http.HttpCode;
import com.lt.tools.results.Results;
import com.lt.tools.results.ResultsConstants;
import com.lt.tools.results.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LT on 2017/8/9.
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    IAuthService authService;
    @Value("${jwt.header}")
    private String tokenHeader;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public Results createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response)
            throws AuthenticationException {
        return authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public Results refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResultsUtil.getErrorResult("token获取失败！");
        } else {
            return ResultsUtil.getSuccessResult().setData(refreshedToken);
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public Results register(@RequestBody BUser addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }

}
