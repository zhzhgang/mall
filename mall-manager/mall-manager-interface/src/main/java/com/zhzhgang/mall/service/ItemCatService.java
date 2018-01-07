package com.zhzhgang.mall.service;

import com.zhzhgang.mall.common.pojo.TreeNode;

import java.util.List;

/**
 * @author zhangzhonggang
 * @create 2018-01-07 15:50
 */
public interface ItemCatService {

    /**
     * 查询商品分类列表
     * @param parentId
     * @return
     */
    List<TreeNode> getItemCatList(long parentId);
}
