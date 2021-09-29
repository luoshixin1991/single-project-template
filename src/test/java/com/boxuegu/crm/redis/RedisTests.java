package com.boxuegu.crm.redis;

import com.boxuegu.crm.manager.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * TODO: 类说明注释
 *
 * @author lsx
 * @date 2021/9/28 18:09
 */
@Slf4j
@SpringBootTest
public class RedisTests {
    @Resource
    private RedisManager redisManager;

    @Test
    public void testLock(){
        log.info("加锁：{}", redisManager.tryLock("lock", "lock", 10));
        log.info("加锁1：{}", redisManager.tryLock("lock1", "lock", 10));
        redisManager.unlock("lock", "lock1");
        while (true){
            log.info("数据：{}, 剩余时间：{}", redisManager.get("lock"), redisManager.getExpire("lock"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
