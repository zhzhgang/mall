package com.zhzhgang.mall.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 索引库维护 Controller
 * @author zhzhgang
 * @create 2018-01-23 20:33
 */
@RestController
public class IndexManagerController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/import")
    public MallResult importIndex() {
        return searchItemService.importItemsToIndex();
    }
}
