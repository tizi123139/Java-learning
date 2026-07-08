package com.example.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMinIdle(10);
        config.setMaxWaitMillis(5000);
        jedisPool = new JedisPool(config, "localhost", 6379, 3000);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
