package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.pojo.PageResult;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理 Controller
 * @author zhangzhonggang
 * @create 2018-01-04 17:06
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public MallItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public PageResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public MallResult save(MallItem mallItem, String desc) {
        return itemService.addItem(mallItem, desc);
    }
}
