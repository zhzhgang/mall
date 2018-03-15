package com.zhzhgang.mall.sso.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.utils.CookieUtils;
import com.zhzhgang.mall.pojo.MallUser;
import com.zhzhgang.mall.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户处理 Controller
 *
 * @author zhzhgang
 * @create 2018-03-14 21:47
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${TOKEN_KEY}")
    private String tokenKey;

    @RequestMapping("/user/check/{param}/{type}")
    public MallResult checkUserData(@PathVariable String param, @PathVariable int type) {
        MallResult result = userService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public MallResult register(MallUser user) {
        MallResult result = userService.register(user);
        return result;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public MallResult login(@RequestBody MallUser user, HttpServletResponse response, HttpServletRequest request) {
        MallResult result = userService.login(user.getUsername(), user.getPassword());

        // 把 token 写入 cookie
        CookieUtils.setCookie(request, response, tokenKey, result.getData().toString());
        return result;
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public MallResult getUserByToken(@PathVariable String token) {
        MallResult user = userService.getUserByToken(token);
        return user;
    }
}
