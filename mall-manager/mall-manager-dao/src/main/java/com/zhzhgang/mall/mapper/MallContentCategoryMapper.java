package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallContentCategory;
import com.zhzhgang.mall.pojo.MallContentCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallContentCategoryMapper {
    int countByExample(MallContentCategoryExample example);

    int deleteByExample(MallContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallContentCategory record);

    int insertSelective(MallContentCategory record);

    List<MallContentCategory> selectByExample(MallContentCategoryExample example);

    MallContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallContentCategory record, @Param("example") MallContentCategoryExample example);

    int updateByExample(@Param("record") MallContentCategory record, @Param("example") MallContentCategoryExample example);

    int updateByPrimaryKeySelective(MallContentCategory record);

    int updateByPrimaryKey(MallContentCategory record);
}