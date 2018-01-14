package com.zhzhgang.mall.content.service;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.pojo.MallContent;

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
}
