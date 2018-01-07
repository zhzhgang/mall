package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.common.pojo.ResponseResult;
import com.zhzhgang.mall.common.pojo.TreeNode;
import com.zhzhgang.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理 Controller
 * @author zhzhgang
 * @create 2018-01-07 20:51
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return itemCatService.getItemCatList(parentId);
    }

}
