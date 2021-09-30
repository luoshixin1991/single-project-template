package com.boxuegu.crm.model.dto;

import lombok.Data;

/**
 * 客户线索信息
 *
 * @author lsx
 * @date 2021/9/30 17:21
 */
@Data
public class CustomerClueDTO {
    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 线索id
     */
    private Long clueId;
}
