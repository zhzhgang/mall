package com.zhzhgang.mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页 Controller
 *
 * @author zhzhgang
 * @create 2018-01-09 22:34
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
