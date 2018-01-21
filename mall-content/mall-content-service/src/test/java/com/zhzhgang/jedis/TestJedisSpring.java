package com.zhzhgang.jedis;

import com.zhzhgang.mall.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhzhgang
 * @create 2018-01-21 11:53
 */
public class TestJedisSpring {

    @Test
    public void testJedisClientPool() {
        // 初始化 Spring 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        // 从容器中获取 JedisClient 对象
        JedisClient jedisClient = ctx.getBean(JedisClient.class);
        jedisClient.set("jedisClient", "myJedisClient");
        String s = jedisClient.get("jedisClient");
        System.out.println("-----------zhzhgang-----------s值 = " + s + ", " + "当前类 = TestJedisSpring.testJedisClientPool()");
    }

}
