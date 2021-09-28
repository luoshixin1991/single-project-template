package com.boxuegu.crm.common.handler;

import com.boxuegu.crm.common.bean.Response;
import com.boxuegu.crm.common.exception.CheckException;
import com.boxuegu.crm.util.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.boxuegu.crm.common.bean.ErrorCode.*;
import static com.boxuegu.crm.common.bean.Response.fail;

/**
 * 全局异常处理
 *
 * @author lsx
 * @date 2021/9/27 16:11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response<Void> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.error(
                "[GlobalExceptionHandler#illegalArgumentExceptionHandler] 参数错误: {}",
                ErrorUtil.getStackTraceAsString(ex)
        );
        return fail(BAD_REQUEST_ERROR, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = CheckException.class)
    public Response<Void> checkExceptionHandler(CheckException ex) {
        log.error("[GlobalExceptionHandler#checkExceptionHandler] 检查错误: {}", ErrorUtil.getStackTraceAsString(ex));
        return fail(CHECK_ERROR, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response<Void> exceptionHandler(Exception ex) {
        log.error("[GlobalExceptionHandler#exceptionHandler] 未捕获异常: {}", ErrorUtil.getStackTraceAsString(ex));
        return fail(UN_CATCH_ERROR);
    }
}
