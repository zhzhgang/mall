package com.zhzhgang.mall.content.service;

import com.zhzhgang.mall.common.pojo.MallResult;
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

    /**
     * 添加内容分类
     * @param parentId: 父类目ID
     * @param name: 分类名
     * @return
     */
    MallResult addContentCategory(Long parentId, String name);
}
