package com.zhzhgang.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void testJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.56.101", 7001));
        nodes.add(new HostAndPort("192.168.56.101", 7002));
        nodes.add(new HostAndPort("192.168.56.101", 7003));
        nodes.add(new HostAndPort("192.168.56.101", 7004));
        nodes.add(new HostAndPort("192.168.56.101", 7005));
        nodes.add(new HostAndPort("192.168.56.101", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1", "abc");
        String s = cluster.get("key1");
        System.out.println("-----------zhzhgang-----------s值 = " + s + ", " + "当前类 = TestJedis.testJedisCluster()");
    }
}
