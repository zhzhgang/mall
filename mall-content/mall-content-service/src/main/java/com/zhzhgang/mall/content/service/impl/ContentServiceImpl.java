package com.zhzhgang.mall.content.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.content.service.ContentService;
import com.zhzhgang.mall.mapper.MallContentMapper;
import com.zhzhgang.mall.pojo.MallContent;
import com.zhzhgang.mall.pojo.MallContentExample;
import com.zhzhgang.mall.pojo.MallContentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    /**
     * 根据内容分类 id 查询内容列表
     *
     * @param cid : 内容分类 id
     * @return
     */
    @Override
    public List<MallContent> getContentByCid(long cid) {
        MallContentExample example = new MallContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<MallContent> list = mallContentMapper.selectByExample(example);
        return list;
    }
}
