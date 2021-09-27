package com.boxuegu.crm.common.bean;

/**
 * http响应码
 *
 * @author lsx
 * @date 2021/9/27 14:42
 */
public enum ErrorCode {
    /**
     *
     */

    OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),
    ;

    private final int code;
    private final String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
