package com.zhzhgang.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhzhgang
 * @create 2018-01-20 19:59
 */
public class TestJedis {

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.56.101", 6379);
        jedis.set("k1", "123");
        String s = jedis.get("k1");
        System.out.println("-----------zhzhgang-----------s值 = " + s + ", " + "当前类 = TestJedis.testJedis()");
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        JedisPool pool = new JedisPool("192.168.56.101", 6379);
        Jedis jedis = pool.getResource();
        String s = jedis.get("k1");
        System.out.println("-----------zhzhgang-----------s值 = " + s + ", " + "当前类 = TestJedis.testJedisPool()");
        jedis.close();
        pool.close();
    }
}
