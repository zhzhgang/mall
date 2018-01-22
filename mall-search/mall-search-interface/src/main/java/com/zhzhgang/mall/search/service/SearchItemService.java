package com.zhzhgang.mall.search.service;

import com.zhzhgang.mall.common.pojo.MallResult;

/**
 * @author zhzhgang
 * @create 2018-01-22 22:11
 */
public interface SearchItemService {

    /**
     * 导入商品数据到数据库
     * @return
     */
    MallResult importItemsToIndex();
}
