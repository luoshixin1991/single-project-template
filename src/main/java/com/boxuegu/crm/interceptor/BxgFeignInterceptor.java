package com.boxuegu.crm.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * BxgFeignApi的请求头设置
 *
 * @author lsx
 * @date 2021/9/23 16:12
 */
@Component
public class BxgFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", "在这里添加请求头");
    }

}
