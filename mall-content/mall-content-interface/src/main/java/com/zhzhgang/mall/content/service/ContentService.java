package com.zhzhgang.mall.content.service;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.pojo.MallContent;

import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-14 22:40
 */
public interface ContentService {

    /**
     * 添加内容
     * @param content
     * @return
     */
    MallResult addContent(MallContent content);

    /**
     * 根据内容分类 id 查询内容列表
     * @param cid: 内容分类 id
     * @return
     */
    List<MallContent> getContentByCid(long cid);
}
