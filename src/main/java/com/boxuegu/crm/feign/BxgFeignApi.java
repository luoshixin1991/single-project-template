package com.boxuegu.crm.feign;

import com.boxuegu.crm.feign.fallback.BxgFeignFallbackFactory;
import com.boxuegu.crm.interceptor.BxgFeignInterceptor;
import com.boxuegu.crm.model.dto.BxgResponse;
import com.boxuegu.crm.model.dto.SuccessOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 博学谷api
 *
 * @author lsx
 * @date 2021/9/22 15:12
 */
@FeignClient(
        name = "bxgFeignApi",
        url = "${boxuegu.url}",
        fallbackFactory = BxgFeignFallbackFactory.class,
        configuration = BxgFeignInterceptor.class
)
public interface BxgFeignApi {

    /**
     * 根据订单id查询首次交齐学费的订单id和首次成功关单的订单id
     *
     * @param orderId 订单id
     * @return com.boxuegu.crm.model.dto.SuccessOrderDTO
     */
    @GetMapping("/crm/order/first/transfer")
    BxgResponse<SuccessOrderDTO> findFirstOrderTransfer(@RequestParam(value = "orderId") String orderId);

}
