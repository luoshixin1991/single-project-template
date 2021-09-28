package com.boxuegu.crm.common.bean;

/**
 * http响应码，自定义异常code从10000开始
 *
 * @author lsx
 * @date 2021/9/27 14:42
 */
public enum ErrorCode {
    /**
     *
     */
    OK(200, "OK"),
    BAD_REQUEST_ERROR(400,"Bad Request"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),
    UN_CATCH_ERROR(10000, "未捕获异常"),
    CHECK_ERROR(10001, "check异常"),
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
