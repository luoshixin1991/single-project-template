package com.boxuegu.crm.service.impl;

import com.boxuegu.crm.model.entity.User;
import com.boxuegu.crm.service.intf.TestCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 此类用于展示Spring Boot缓存注解@Cacheable、@CacheEvict的使用
 *
 * @author lsx
 * @date 2021/9/29 18:03
 */
@Slf4j
@Service
public class TestCacheServiceImpl implements TestCacheService {
    @Cacheable(value = "crm:users", key = "#id")
    @Override
    public User findUserById(Long id) {
        log.info("调用findUserById方法");
        if (!Objects.equals(1L, id)){
            return null;
        }

        return User.builder().id(1L).name("李斯").build();
    }

    @CacheEvict(value = "crm:users", key = "#user.id")
    @Override
    public void updateUserById(User user) {
        log.info("调用updateUserById方法");
    }
}
