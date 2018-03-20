package com.zhzhgang.mall.order.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.jedis.JedisClient;
import com.zhzhgang.mall.mapper.MallOrderItemMapper;
import com.zhzhgang.mall.mapper.MallOrderMapper;
import com.zhzhgang.mall.mapper.MallOrderShippingMapper;
import com.zhzhgang.mall.order.pojo.OrderInfo;
import com.zhzhgang.mall.order.service.OrderService;
import com.zhzhgang.mall.pojo.MallOrderItem;
import com.zhzhgang.mall.pojo.MallOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单处理 Service
 * @author zhzhgang
 * @create 2018-03-20 22:14
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private MallOrderItemMapper mallOrderItemMapper;

    @Autowired
    private MallOrderShippingMapper mallOrderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_ID_GEN_KEY}")
    private String orderIdGenKey;

    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String orderItemIdGenKey;

    @Value("${ORDER_ID_BEGIN_VALUE}")
    private String orderIdBeginValue;

    /**
     * 创建订单
     *
     * @param orderInfo
     * @return
     */
    @Override
    public MallResult createOrder(OrderInfo orderInfo) {

        // 生成订单号，使用 Redis 的 incr 生成
        if (!jedisClient.exists(orderIdGenKey)) {
            jedisClient.set(orderIdGenKey, orderIdBeginValue);
        }
        String orderId = jedisClient.incr(orderIdGenKey).toString();

        // 补全属性，向订单表插入数据
        orderInfo.setOrderId(orderId);
        orderInfo.setPostFee("0");
        // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        mallOrderMapper.insert(orderInfo);

        // 向订单明细表插入数据
        List<MallOrderItem> orderItems = orderInfo.getOrderItems();
        for (MallOrderItem orderItem : orderItems) {
            String oId = jedisClient.incr(orderItemIdGenKey).toString();
            orderItem.setId(oId);
            orderItem.setOrderId(orderId);
            mallOrderItemMapper.insert(orderItem);
        }

        // 向订单物流表插入数据
        MallOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        mallOrderShippingMapper.insert(orderShipping);

        return MallResult.ok(orderId);
    }
}
