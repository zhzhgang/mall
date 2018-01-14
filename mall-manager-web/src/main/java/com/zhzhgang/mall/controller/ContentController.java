package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.content.service.ContentService;
import com.zhzhgang.mall.pojo.MallContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理 Controller
 * @author zhzhgang
 * @create 2018-01-14 20:40
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public MallResult addContent(MallContent mallContent) {
        return contentService.addContent(mallContent);
    }
}
