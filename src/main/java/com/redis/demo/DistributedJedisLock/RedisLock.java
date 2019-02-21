package com.redis.demo.DistributedJedisLock;

import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisLock {
    String lockKey;
    String value = UUID.randomUUID().toString();
    Jedis jedis;
    int timeOut;
    boolean isOpen = true;

    public  RedisLock(String lockKey, Jedis jedis, int timeOut) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.timeOut = timeOut;
    }

    public void lock()  {
      while (true) {
          if (tryLock()) {
              break;
          }
      }

    }

    public void lockInterrupt() throws InterruptedException {
        while (true) {
            boolean interrupted = Thread.currentThread().isInterrupted();
            if (interrupted) {
                throw new InterruptedException("中断");
            }
            if (tryLock()) {
                break;
            }
        }
    }

    public boolean tryLock() {
        String result = jedis.set(lockKey,value,"NX","EX",timeOut);
        if ("OK".equals(result)) {
            System.out.println("线程id:"+Thread.currentThread().getId() + "加锁成功!时间:"+System.currentTimeMillis());
            if(isOpen) {
                new Thread(new growthTimeout()).start();
            }
            return true;
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return  false;
            }
        }
        return false;
    }

    private class growthTimeout implements Runnable {

        @Override
        public void run() {
            while (true) {
                String ctl = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                Object re = jedis.eval(ctl,1,lockKey,value,"3000");
                if("0".equals(re.toString())) {
                    break;
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    public void unLock() {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) " +
                "else " +
                "return 0 end";
        jedis.eval(script,1,lockKey,value);

        System.out.println("线程id:"+Thread.currentThread().getId() + "解锁成功!时间"+System.currentTimeMillis());
    }

}
