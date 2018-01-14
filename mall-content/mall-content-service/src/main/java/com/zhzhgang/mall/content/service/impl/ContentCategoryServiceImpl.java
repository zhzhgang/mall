package com.zhzhgang.mall.content.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.pojo.TreeNode;
import com.zhzhgang.mall.content.service.ContentCategoryService;
import com.zhzhgang.mall.mapper.MallContentCategoryMapper;
import com.zhzhgang.mall.pojo.MallContentCategory;
import com.zhzhgang.mall.pojo.MallContentCategoryExample;
import com.zhzhgang.mall.pojo.MallContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 添加内容分类
     *
     * @param parentId : 父类目ID
     * @param name     : 分类名
     * @return
     */
    @Override
    public MallResult addContentCategory(Long parentId, String name) {
        MallContentCategory contentCategory = new MallContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        // 内容分类状态：1-正常；2-删除
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setIsParent(false);

        mallContentCategoryMapper.insert(contentCategory);

        // 判断并设置父节点状态
        MallContentCategory parentContentCategory = mallContentCategoryMapper.selectByPrimaryKey(parentId);
        if (parentContentCategory != null && !parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            mallContentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }

        return MallResult.ok(contentCategory);
    }
}
