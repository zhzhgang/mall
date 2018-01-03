package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallContent;
import com.zhzhgang.mall.pojo.MallContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallContentMapper {
    int countByExample(MallContentExample example);

    int deleteByExample(MallContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallContent record);

    int insertSelective(MallContent record);

    List<MallContent> selectByExampleWithBLOBs(MallContentExample example);

    List<MallContent> selectByExample(MallContentExample example);

    MallContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallContent record, @Param("example") MallContentExample example);

    int updateByExampleWithBLOBs(@Param("record") MallContent record, @Param("example") MallContentExample example);

    int updateByExample(@Param("record") MallContent record, @Param("example") MallContentExample example);

    int updateByPrimaryKeySelective(MallContent record);

    int updateByPrimaryKeyWithBLOBs(MallContent record);

    int updateByPrimaryKey(MallContent record);
}