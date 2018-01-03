package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallItemDesc;
import com.zhzhgang.mall.pojo.MallItemDescExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallItemDescMapper {
    int countByExample(MallItemDescExample example);

    int deleteByExample(MallItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(MallItemDesc record);

    int insertSelective(MallItemDesc record);

    List<MallItemDesc> selectByExampleWithBLOBs(MallItemDescExample example);

    List<MallItemDesc> selectByExample(MallItemDescExample example);

    MallItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") MallItemDesc record, @Param("example") MallItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") MallItemDesc record, @Param("example") MallItemDescExample example);

    int updateByExample(@Param("record") MallItemDesc record, @Param("example") MallItemDescExample example);

    int updateByPrimaryKeySelective(MallItemDesc record);

    int updateByPrimaryKeyWithBLOBs(MallItemDesc record);

    int updateByPrimaryKey(MallItemDesc record);
}