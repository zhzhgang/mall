package com.zhzhgang.mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhzhgang
 * @create 2018-02-06 22:13
 */
public class TestSpringActiveMq {

    @Test
    public void testSpringActiveMq() throws Exception {
        // 初始化 Spring 容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        // 等待
        System.in.read();
    }
}
