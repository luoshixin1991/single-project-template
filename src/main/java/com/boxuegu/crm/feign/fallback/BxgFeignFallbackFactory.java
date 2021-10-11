package com.boxuegu.crm.feign.fallback;

import com.boxuegu.crm.feign.BxgFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * BxgFeignApi的熔断处理工厂
 *
 * @author lsx
 * @date 2021/9/23 11:35
 */
@Slf4j
@Component
public class BxgFeignFallbackFactory implements FallbackFactory<BxgFeignApi> {
    @Resource
    private BxgFeignHystrix bxgFeignHystrix;

    @Override
    public BxgFeignApi create(Throwable cause) {
        log.error("[BxgFeignFallbackFactory#create] 调用博学谷接口失败", cause);
        return bxgFeignHystrix;
    }
}
