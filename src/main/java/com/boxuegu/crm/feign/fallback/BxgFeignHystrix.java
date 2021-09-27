package com.boxuegu.crm.feign.fallback;

import com.boxuegu.crm.feign.BxgFeignApi;
import com.boxuegu.crm.model.response.BxgResponse;
import com.boxuegu.crm.model.dto.SuccessOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * BxgFeignApi的降级处理类
 *
 * @author lsx
 * @date 2021/9/22 17:23
 */
@Slf4j
@Component
public class BxgFeignHystrix implements BxgFeignApi {
    @Override
    public BxgResponse<SuccessOrderDTO> findFirstOrderTransfer(String orderId) {
        log.info("[BxgFeignHystrix#findFirstOrderTransfer] 降级处理，参数orderId={}", orderId);
        return null;
    }
}
