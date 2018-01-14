package com.zhzhgang.mall.content.service.impl;

import com.zhzhgang.mall.common.pojo.TreeNode;
import com.zhzhgang.mall.content.service.ContentCategoryService;
import com.zhzhgang.mall.mapper.MallContentCategoryMapper;
import com.zhzhgang.mall.pojo.MallContentCategory;
import com.zhzhgang.mall.pojo.MallContentCategoryExample;
import com.zhzhgang.mall.pojo.MallContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-10 22:57
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private MallContentCategoryMapper mallContentCategoryMapper;

    /**
     * 查询内容分类列表
     *
     * @param parentId : 父类目ID
     * @return
     */
    @Override
    public List<TreeNode> getContentCategoryList(long parentId) {
        MallContentCategoryExample example = new MallContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<MallContentCategory> list = mallContentCategoryMapper.selectByExample(example);

        List<TreeNode> treeNodeList = new ArrayList<>();
        for (MallContentCategory mallContentCategory : list) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(mallContentCategory.getId());
            treeNode.setText(mallContentCategory.getName());
            treeNode.setState(mallContentCategory.getIsParent() ? "closed" : "open");
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }
}
