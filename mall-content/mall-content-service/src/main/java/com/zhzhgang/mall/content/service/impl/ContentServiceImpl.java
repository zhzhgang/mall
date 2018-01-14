package com.zhzhgang.mall.content.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.content.service.ContentService;
import com.zhzhgang.mall.mapper.MallContentMapper;
import com.zhzhgang.mall.pojo.MallContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;

/**
 * @author zhzhgang
 * @create 2018-01-14 20:29
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private MallContentMapper mallContentMapper;

    /**
     * 添加内容
     *
     * @param content
     * @return
     */
    @Override
    public MallResult addContent(MallContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        mallContentMapper.insert(content);
        return MallResult.ok();
    }
}
