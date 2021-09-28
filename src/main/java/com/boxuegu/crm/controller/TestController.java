package com.boxuegu.crm.controller;

import com.boxuegu.crm.common.bean.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.boxuegu.crm.common.bean.Response.success;

/**
 * 测试controller类
 *
 * @author lsx
 * @date 2021/9/27 18:11
 */
@Slf4j
@RestController
public class TestController {
    /**
     * 单个方法的总行数不超过80
     */
    @GetMapping(value = "/test")
    public Response<Void> test1(){
        return success();
    }
}
