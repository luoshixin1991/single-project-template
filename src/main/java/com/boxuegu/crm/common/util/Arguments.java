package com.boxuegu.crm.common.util;

import com.boxuegu.crm.common.exception.CheckException;


/**
 * 参数校验
 *
 * @author lsx
 * @date 2021/9/27 15:31
 */
public class Arguments {
    private Arguments() {
    }

    /**
     * 检查表达式是否成立
     *
     * @param expression true 放行，false 抛出异常
     * @param errorMsg   错误信息
     */
    public static void check(boolean expression, String errorMsg) {
        fail(expression, errorMsg);
    }

    /**
     * @param expression true 放行，false 抛出异常
     * @param errorMsg   错误信息
     */
    private static void fail(boolean expression, String errorMsg) {
        if (!expression) {
            throw new CheckException(errorMsg);
        }
    }
}
