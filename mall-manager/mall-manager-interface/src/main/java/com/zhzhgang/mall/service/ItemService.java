package com.zhzhgang.mall.service;

import com.zhzhgang.mall.common.pojo.ResponseResult;
import com.zhzhgang.mall.pojo.MallItem;

/**
 * @author zhangzhonggang
 * @create 2018-01-04 15:50
 */
public interface ItemService {

    MallItem getItemById(long itemId);

    ResponseResult getItemList(int page, int rows);

}
