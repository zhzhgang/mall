package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallItemMapper {
    int countByExample(MallItemExample example);

    int deleteByExample(MallItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallItem record);

    int insertSelective(MallItem record);

    List<MallItem> selectByExample(MallItemExample example);

    MallItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallItem record, @Param("example") MallItemExample example);

    int updateByExample(@Param("record") MallItem record, @Param("example") MallItemExample example);

    int updateByPrimaryKeySelective(MallItem record);

    int updateByPrimaryKey(MallItem record);
}