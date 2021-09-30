package com.boxuegu.crm.service.intf;

import com.boxuegu.crm.model.entity.User;

/**
 * 此类用于展示Spring Boot缓存注解@Cacheable、@CacheEvict的使用
 *
 * @author lsx
 * @date 2021/9/29 17:56
 */
public interface TestCacheService {
    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return com.boxuegu.crm.model.entity.User
     */
    User findUserById(Long id);

    /**
     * 根据用户id修改用户信息
     *
     * @param user 用户信息
     */
    void updateUserById(User user);
}
