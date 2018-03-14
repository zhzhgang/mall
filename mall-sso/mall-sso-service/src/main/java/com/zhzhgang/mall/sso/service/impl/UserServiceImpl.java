package com.zhzhgang.mall.sso.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.mapper.MallUserMapper;
import com.zhzhgang.mall.pojo.MallUser;
import com.zhzhgang.mall.pojo.MallUserExample;
import com.zhzhgang.mall.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户处理 Service
 *
 * @author zhzhgang
 * @create 2018-03-14 21:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MallUserMapper userMapper;

    /**
     * 检查数据是否可用
     *
     * @param data
     * @param type
     * @return
     */
    @Override
    public MallResult checkData(String data, int type) {
        MallUserExample example = new MallUserExample();
        MallUserExample.Criteria criteria = example.createCriteria();

        if (type == 1) {
            criteria.andUsernameEqualTo(data);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(data);
        } else if (type == 3) {
            criteria.andEmailEqualTo(data);
        } else {
            return MallResult.build(400, "非法数据");
        }

        List<MallUser> userList = userMapper.selectByExample(example);

        if (userList != null && userList.size() > 0) {
            return MallResult.ok(false);
        }

        return MallResult.ok(true);
    }
}
