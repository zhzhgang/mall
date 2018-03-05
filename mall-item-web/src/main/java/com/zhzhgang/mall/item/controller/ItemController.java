package com.zhzhgang.mall.item.controller;

import com.zhzhgang.mall.item.pojo.Item;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemDesc;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示 Controller
 * @author zhzhgang
 * @create 2018-03-05 22:14
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        // 取商品基本信息
        MallItem mallItem = itemService.getItemById(itemId);
        Item item = new Item(mallItem);

        // 取商品详情
        MallItemDesc mallItemDesc = itemService.getItemDescById(itemId);

        // 把数据传递给页面
        model.addAttribute("item", mallItem);
        model.addAttribute("itemDesc", mallItemDesc);

        // 返回逻辑视图
        return "item";
    }
}
