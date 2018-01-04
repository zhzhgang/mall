package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
