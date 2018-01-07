package com.zhzhgang.mall.service.impl;

import com.zhzhgang.mall.common.pojo.TreeNode;
import com.zhzhgang.mall.mapper.MallItemCatMapper;
import com.zhzhgang.mall.pojo.MallItemCat;
import com.zhzhgang.mall.pojo.MallItemCatExample;
import com.zhzhgang.mall.pojo.MallItemCatExample.Criteria;
import com.zhzhgang.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-07 18:21
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {


    @Autowired
    private MallItemCatMapper mallItemCatMapper;

    @Override
    public List<TreeNode> getItemCatList(long parentId) {

        MallItemCatExample example = new MallItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<MallItemCat> list = mallItemCatMapper.selectByExample(example);

        List<TreeNode> treeNodeList = new ArrayList<>();
        for (MallItemCat mallItemCat : list) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(mallItemCat.getId());
            treeNode.setText(mallItemCat.getName());
            treeNode.setState(mallItemCat.getIsParent() ? "closed" : "open");
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }
}
