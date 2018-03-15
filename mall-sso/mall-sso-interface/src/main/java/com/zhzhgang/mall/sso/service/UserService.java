package com.zhzhgang.mall.sso.service;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.pojo.MallUser;

/**
 * @author zhzhgang
 * @create 2018-03-14 21:29
 */
public interface UserService {

    /**
     * 检查数据是否可用
     * @param data
     * @param type
     * @return
     */
    MallResult checkData(String data, int type);

    /**
     * 注册
     * @param user
     * @return
     */
    MallResult register(MallUser user);
}
