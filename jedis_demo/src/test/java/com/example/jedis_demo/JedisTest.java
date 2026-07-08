package com.example.jedis_demo;

import com.example.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    public void setUp() {
        //jedis = new Jedis("localhost", 6379);
        jedis = JedisConnectionFactory.getJedis();

        jedis.select(0);
    }

    @Test
    void testString() {
        String result = jedis.set("name", "hahaha");
        System.out.println(result);

        String name = jedis.get("name");
        System.out.println(name);
    }

    @AfterEach
    public void tearDown() {
        if(jedis != null) {
            jedis.close();
        }
    }

    @Test
    void testHash(){
        jedis.hset("user:1", "name", "hahaha");
        jedis.hset("user:1", "name2", "hahaha");

        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);

    }



}
