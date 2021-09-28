package com.boxuegu.crm;

import com.boxuegu.crm.feign.BxgFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static com.boxuegu.crm.common.util.CheckUtil.check;

@Slf4j
@SpringBootTest
class ApplicationTests {

    @Resource
    private BxgFeignApi bxgFeignApi;

    @Test
    void testFeignApi(){
        log.info("数据：{}", bxgFeignApi.findFirstOrderTransfer("7710af3936ad46b98497f10b9a3aa4bf"));
        log.warn("这是warn");
        log.debug("这是debug");
        log.error("这是error");
    }

    @Test
    void contextLoads() {

        check(false, "空指针", IllegalArgumentException.class);

    }
}
