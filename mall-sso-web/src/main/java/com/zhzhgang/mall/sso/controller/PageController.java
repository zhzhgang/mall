package com.zhzhgang.mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示登录和注册页面的 Controller
 *
 * @author zhzhgang
 * @create 2018-03-15 22:04
 */
@Controller
public class PageController {

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    @RequestMapping("/page/login")
    public String showLogin(String url, Model model) {
        model.addAttribute("redirect", url);
        return "login";
    }
}
