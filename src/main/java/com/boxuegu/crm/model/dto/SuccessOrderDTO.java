package com.boxuegu.crm.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 根据订单id查询首次交齐学费的订单id和首次成功关单的订单id结果
 *
 * @author lsx
 * @date 2021/9/22 16:50
 */
@Data
@ToString
public class SuccessOrderDTO {

    /**
     * 首次交齐学费的订单id
     */
    private String firstPaidAllOrderId;
    /**
     * 首次成功关单的订单id
     */
    private String firstSuccessCloseOrderId;

}
