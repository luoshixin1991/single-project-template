package com.boxuegu.crm.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * OpenFeign请求信息打印配置，生产环境可以不用，或者不用配置此类
 *
 * @author lsx
 * @date 2021/9/23 14:48
 */
@Configuration
public class OpenFeignLogConfig {

    @Value("${spring.profiles.active}")
    private String profilesActive;

    /**
     * 在生产环境，需要打印feign的日志，使用basic级别就ok了，强烈不建议使用full。打印日志太多，消耗feign的性能
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        String prod = "prod";
        if (Objects.equals(profilesActive, prod)){
            return Logger.Level.BASIC;
        }

        return Logger.Level.FULL;
    }
}
