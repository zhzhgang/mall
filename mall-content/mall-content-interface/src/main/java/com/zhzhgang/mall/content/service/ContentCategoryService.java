package com.zhzhgang.mall.content.service;

import com.zhzhgang.mall.common.pojo.TreeNode;

import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-10 22:40
 */
public interface ContentCategoryService {

    /**
     * 查询内容分类列表
     * @param parentId: 父类目ID
     * @return
     */
    List<TreeNode> getContentCategoryList(long parentId);
}
