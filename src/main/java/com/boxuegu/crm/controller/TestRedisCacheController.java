package com.boxuegu.crm.controller;

import com.boxuegu.crm.common.bean.Response;
import com.boxuegu.crm.model.entity.User;
import com.boxuegu.crm.service.intf.TestCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import static com.boxuegu.crm.common.bean.Response.success;

/**
 * 测试controller类
 *
 * @author lsx
 * @date 2021/9/27 18:11
 */
@Slf4j
@RestController
public class TestRedisCacheController {
    @Resource
    private TestCacheService testCacheService;

    /**
     * 单个方法的总行数不超过80
     */
    @GetMapping("/users/{userId}")
    public Response<User> findUserById(@PathVariable Long userId){
        return success(testCacheService.findUserById(userId));
    }

    @PutMapping("/users/{userId}")
    public Response<Void> updateUserById(@PathVariable Long userId, @RequestBody User user){
        user.setId(userId);
        testCacheService.updateUserById(user);
        return success();
    }
}
