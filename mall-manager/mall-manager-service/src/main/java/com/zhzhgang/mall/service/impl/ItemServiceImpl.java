package com.zhzhgang.mall.service.impl;

import com.zhzhgang.mall.mapper.MallItemMapper;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品管理 Service
 * @author zhangzhonggang
 * @create 2018-01-04 16:00
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    MallItemMapper mallItemMapper;

    @Override
    public MallItem getItemById(long itemId) {
        return mallItemMapper.selectByPrimaryKey(itemId);
    }

}
