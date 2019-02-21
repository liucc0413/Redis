package com.redis.demo.DistributedJedisLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTool {
    private static Jedis jedis = getJedis();
    private static Jedis getJedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        /*配置连接池最大空闲数*/
        config.setMaxIdle(50);

        /*配置连接池最大连接数*/
        config.setMaxTotal(100);

        /*最大等待毫秒数*/
        config.setMaxWaitMillis(20000);

        /*使用配置创建连接池*/
        JedisPool jedisPool = new JedisPool(config, "localhost");

        /*从连接池中获取单个链接*/
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public static Jedis getJedisInstance() {
        return jedis;
    }

}
