package com.zhzhgang.mall.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收 Activemq 消息的监听器
 *
 * @author zhzhgang
 * @create 2018-02-06 22:01
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("-----------zhzhgang-----------text值 = " + text + ", " + "当前类 = MyMessageListener.onMessage()");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
