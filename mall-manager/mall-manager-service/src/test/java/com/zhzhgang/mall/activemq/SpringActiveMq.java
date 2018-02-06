package com.zhzhgang.mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * @author zhzhgang
 * @create 2018-02-06 21:38
 */
public class SpringActiveMq {


    // 使用 JmsTemplate 发送消息
    @Test
    public void testJmsTemplate() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        Destination destination = (Destination) applicationContext.getBean("test-queue");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("JmsTemplate send Message2");
                return message;
            }
        });
    }
}
