package com.zhzhgang.mall.mapper;

import com.zhzhgang.mall.pojo.MallUser;
import com.zhzhgang.mall.pojo.MallUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserMapper {
    int countByExample(MallUserExample example);

    int deleteByExample(MallUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallUser record);

    int insertSelective(MallUser record);

    List<MallUser> selectByExample(MallUserExample example);

    MallUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByExample(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);
}