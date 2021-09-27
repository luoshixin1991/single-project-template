package com.boxuegu.crm.common.bean;

import java.io.Serializable;

/**
 * web层相应包装类
 *
 * @author lsx
 * @date 2021/9/27 14:34
 */
public class Response<T> implements Serializable {
    /**
     * http状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    private Response() {
    }

    /**
     * 成功的响应结果
     */
    public static <T> Response<T> success() {
        return response(ErrorCode.OK, null);
    }

    /**
     * 成功的响应结果
     *
     * @param data 返回的业务数据
     */
    public static <T> Response<T> success(T data) {
        return response(ErrorCode.OK, data);
    }

    /**
     * 失败的响应结果
     *
     * @param errorCode 错误码
     */
    public static <T> Response<T> fail(ErrorCode errorCode){
        return response(errorCode, null);
    }

    /**
     * 响应结果
     *
     * @param errorCode http响应码
     * @param data      返回的业务数据
     */
    private static <T> Response<T> response(ErrorCode errorCode, T data) {
        Response<T> resp = new Response<>();
        resp.setCode(errorCode.getCode());
        resp.setMessage(errorCode.getDesc());
        resp.setData(data);
        return resp;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
