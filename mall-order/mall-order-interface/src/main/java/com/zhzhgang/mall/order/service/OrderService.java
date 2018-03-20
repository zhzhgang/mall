package com.zhzhgang.mall.order.service;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.order.pojo.OrderInfo;

public interface OrderService {

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    MallResult createOrder(OrderInfo orderInfo);
}
