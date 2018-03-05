package com.zhzhgang.mall.service;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.pojo.PageResult;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemDesc;

/**
 * @author zhangzhonggang
 * @create 2018-01-04 15:50
 */
public interface ItemService {

    MallItem getItemById(long itemId);

    PageResult getItemList(int page, int rows);

    /**
     * 添加商品
     * @param mallItem: 商品对象
     * @param desc: 商品描述
     * @return MallResult
     */
    MallResult addItem(MallItem mallItem, String desc);

    MallItemDesc getItemDescById(long itemId);

}
