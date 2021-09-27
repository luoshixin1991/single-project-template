package com.boxuegu.crm.model.response;

import lombok.*;

/**
 * 博学谷api响应结果包装类
 *
 * @author lsx
 * @date 2021/9/22 16:50
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BxgResponse<T> {
    /**
     * 状态码
     */
    private int status;
    /**
     * 消息
     */
    private String message;
    /**
     * 结果
     */
    private T result;
}
