package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallOrderShipping;
import com.zhzhgang.mall.pojo.MallOrderShippingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderShippingMapper {
    int countByExample(MallOrderShippingExample example);

    int deleteByExample(MallOrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(MallOrderShipping record);

    int insertSelective(MallOrderShipping record);

    List<MallOrderShipping> selectByExample(MallOrderShippingExample example);

    MallOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") MallOrderShipping record, @Param("example") MallOrderShippingExample example);

    int updateByExample(@Param("record") MallOrderShipping record, @Param("example") MallOrderShippingExample example);

    int updateByPrimaryKeySelective(MallOrderShipping record);

    int updateByPrimaryKey(MallOrderShipping record);
}