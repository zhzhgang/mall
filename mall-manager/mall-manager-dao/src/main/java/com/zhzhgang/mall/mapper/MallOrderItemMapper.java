package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallOrderItem;
import com.zhzhgang.mall.pojo.MallOrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderItemMapper {
    int countByExample(MallOrderItemExample example);

    int deleteByExample(MallOrderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(MallOrderItem record);

    int insertSelective(MallOrderItem record);

    List<MallOrderItem> selectByExample(MallOrderItemExample example);

    MallOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MallOrderItem record, @Param("example") MallOrderItemExample example);

    int updateByExample(@Param("record") MallOrderItem record, @Param("example") MallOrderItemExample example);

    int updateByPrimaryKeySelective(MallOrderItem record);

    int updateByPrimaryKey(MallOrderItem record);
}