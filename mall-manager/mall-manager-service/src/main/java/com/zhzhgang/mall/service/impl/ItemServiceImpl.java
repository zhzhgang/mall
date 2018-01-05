package com.zhzhgang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhzhgang.mall.common.pojo.ResponseResult;
import com.zhzhgang.mall.mapper.MallItemMapper;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemExample;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResponseResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);

        MallItemExample example = new MallItemExample();
        List<MallItem> list = mallItemMapper.selectByExample(example);

        PageInfo<MallItem> pageInfo = new PageInfo<MallItem>(list);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setRows(list);
        responseResult.setTotal(pageInfo.getTotal());
        return responseResult;
    }
}
