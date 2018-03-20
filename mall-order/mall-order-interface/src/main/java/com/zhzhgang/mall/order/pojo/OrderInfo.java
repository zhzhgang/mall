package com.zhzhgang.mall.order.pojo;

import com.zhzhgang.mall.pojo.MallOrder;
import com.zhzhgang.mall.pojo.MallOrderItem;
import com.zhzhgang.mall.pojo.MallOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-03-20 21:46
 */
public class OrderInfo extends MallOrder implements Serializable {

    private List<MallOrderItem> orderItems;

    private MallOrderShipping orderShipping;

    public List<MallOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MallOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public MallOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(MallOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
