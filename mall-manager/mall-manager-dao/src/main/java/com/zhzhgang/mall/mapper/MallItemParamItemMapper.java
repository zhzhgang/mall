package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallItemParamItem;
import com.zhzhgang.mall.pojo.MallItemParamItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallItemParamItemMapper {
    int countByExample(MallItemParamItemExample example);

    int deleteByExample(MallItemParamItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallItemParamItem record);

    int insertSelective(MallItemParamItem record);

    List<MallItemParamItem> selectByExampleWithBLOBs(MallItemParamItemExample example);

    List<MallItemParamItem> selectByExample(MallItemParamItemExample example);

    MallItemParamItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallItemParamItem record, @Param("example") MallItemParamItemExample example);

    int updateByExampleWithBLOBs(@Param("record") MallItemParamItem record, @Param("example") MallItemParamItemExample example);

    int updateByExample(@Param("record") MallItemParamItem record, @Param("example") MallItemParamItemExample example);

    int updateByPrimaryKeySelective(MallItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(MallItemParamItem record);

    int updateByPrimaryKey(MallItemParamItem record);
}