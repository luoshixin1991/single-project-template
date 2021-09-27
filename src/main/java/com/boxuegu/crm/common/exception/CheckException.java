package com.boxuegu.crm.common.exception;

/**
 * 检查数据异常
 *
 * @author lsx
 * @date 2021/9/27 15:37
 */
public class CheckException extends RuntimeException{
    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
