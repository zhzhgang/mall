package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallItemCat;
import com.zhzhgang.mall.pojo.MallItemCatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallItemCatMapper {
    int countByExample(MallItemCatExample example);

    int deleteByExample(MallItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallItemCat record);

    int insertSelective(MallItemCat record);

    List<MallItemCat> selectByExample(MallItemCatExample example);

    MallItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallItemCat record, @Param("example") MallItemCatExample example);

    int updateByExample(@Param("record") MallItemCat record, @Param("example") MallItemCatExample example);

    int updateByPrimaryKeySelective(MallItemCat record);

    int updateByPrimaryKey(MallItemCat record);
}