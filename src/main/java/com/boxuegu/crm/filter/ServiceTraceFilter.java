package com.boxuegu.crm.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 生成应用自己的tranceId
 *
 * @author lsx
 * @date 2021/10/7 16:13
 */
@Component
public class ServiceTraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String serviceTraceId = "serviceTraceId";
        MDC.put(serviceTraceId, getTraceId());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        /*
         避免如果是一个线程池，池中的线程会被重复利用的情况下，如果你没有结束后清除MDC的信息，
         那么在下次设置之前，上次设置之后的这段日志，就会出现分类错乱的情况。
         */
        MDC.clear();
    }

    private String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        return timestamp + uuid.toString().replace("-", "");
    }
}
