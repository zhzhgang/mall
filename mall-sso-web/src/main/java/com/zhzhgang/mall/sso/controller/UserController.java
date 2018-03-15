package com.zhzhgang.mall.sso.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.pojo.MallUser;
import com.zhzhgang.mall.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/user/check/{param}/{type}")
    public MallResult checkUserData(@PathVariable String param, @PathVariable int type) {
        MallResult result = userService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public MallResult register(@RequestBody MallUser user) {
        MallResult result = userService.register(user);
        return result;
    }
}
