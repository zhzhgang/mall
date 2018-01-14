package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.common.pojo.TreeNode;
import com.zhzhgang.mall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理 Controller
 * @author zhzhgang
 * @create 2018-01-14 14:49
 */
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<TreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCategoryList(parentId);
    }
}
