package com.boxuegu.crm;

import com.boxuegu.crm.property.RocketMqProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * TODO: 类说明注释
 *
 * @author lsx
 * @date 2021/10/6 17:04
 */
@Slf4j
@SpringBootTest
public class PropertyTest {
    @Resource
    private RocketMqProperties rocketMqProperties;

    @Test
    public void test(){
        log.info("数据：{}", rocketMqProperties);
    }
}
