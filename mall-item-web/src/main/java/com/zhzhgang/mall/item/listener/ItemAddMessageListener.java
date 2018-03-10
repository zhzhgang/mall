package com.zhzhgang.mall.item.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author zhzhgang
 * @create 2018-03-10 20:28
 */
public class ItemAddMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        // 从消息中取商品 ID
        // 根据商品 ID 查询商品信息及商品描述
        // 使用 freemarker 生成静态页面
        // 1.创建模板
        // 2.加载模板对象
        // 3.准备模板需要的数据
        // 4.指定输出的目录及文件名
        // 5.生成静态页面
    }
}
