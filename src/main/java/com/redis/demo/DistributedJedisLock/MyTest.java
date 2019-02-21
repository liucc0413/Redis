package com.redis.demo.DistributedJedisLock;

import redis.clients.jedis.Jedis;

public class MyTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = RedisTool.getJedisInstance();

        RedisLock lock = new RedisLock("lcc",jedis,1000);
        new Thread(() ->{
            lock.lock();

            try {
                System.out.println("lock1");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unLock();
        }).start();
        Thread.sleep(500);


        /*调用克中断lock方法*/
        Thread tt = new Thread(() ->{


            try {
                lock.lockInterrupt();
                System.out.println("lock2");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unLock();
        });
        tt.start();
        tt.interrupt();

    }
}
