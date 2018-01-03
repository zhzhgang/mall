package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallItemParam;
import com.zhzhgang.mall.pojo.MallItemParamExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallItemParamMapper {
    int countByExample(MallItemParamExample example);

    int deleteByExample(MallItemParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallItemParam record);

    int insertSelective(MallItemParam record);

    List<MallItemParam> selectByExampleWithBLOBs(MallItemParamExample example);

    List<MallItemParam> selectByExample(MallItemParamExample example);

    MallItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallItemParam record, @Param("example") MallItemParamExample example);

    int updateByExampleWithBLOBs(@Param("record") MallItemParam record, @Param("example") MallItemParamExample example);

    int updateByExample(@Param("record") MallItemParam record, @Param("example") MallItemParamExample example);

    int updateByPrimaryKeySelective(MallItemParam record);

    int updateByPrimaryKeyWithBLOBs(MallItemParam record);

    int updateByPrimaryKey(MallItemParam record);
}