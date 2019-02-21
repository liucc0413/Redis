package com.redis.demo.Listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyRedisListener implements MessageListener {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        String myBody = (String) redisTemplate.getStringSerializer().deserialize(body);
        System.out.println("消息体:"+myBody);

        byte[] chat = message.getChannel();
        String myChat = redisTemplate.getStringSerializer().deserialize(chat).toString();
        System.out.println("通道名称："+myChat);

        System.out.println(new String(bytes));

    }
}
