package com.redis.demo.Listener;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.security.RunAs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-redis.xml")
public class ListenerMain {
  @Autowired
  RedisTemplate redisTemplate;

    @Test
    public void test () {
        String channel = "chat";
        redisTemplate.convertAndSend(channel,"wo shi lcc");

    }

}
