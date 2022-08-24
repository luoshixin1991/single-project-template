package com.boxuegu.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan("com.boxuegu.crm.property")
@MapperScan("com.boxuegu.crm.mapper")
@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }
}
