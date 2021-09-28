package com.boxuegu.crm.common.util;

import com.boxuegu.crm.common.exception.CheckException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * 校验工具类，默认抛出CheckException异常，也可指定异常
 *
 * @author lsx
 * @date 2021/9/27 15:31
 */
public class CheckUtil {
    private CheckUtil() {
    }

    /**
     * 检查方法参数是否合法
     *
     * @param expression true 放行，false 抛出异常
     * @param errorMsg   错误信息
     */
    public static void checkParam(boolean expression, String errorMsg) {
        fail(expression, errorMsg, IllegalArgumentException.class);
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
     * 检查表达式是否成立
     *
     * @param expression      true 放行，false 抛出异常
     * @param errorMsg        错误信息
     * @param expressionClazz 抛出指定异常
     */
    public static <T> void check(boolean expression, String errorMsg, Class<T> expressionClazz) {
        fail(expression, errorMsg, expressionClazz);
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

    /**
     * @param expression      true 放行，false 抛出异常
     * @param errorMsg        错误信息
     * @param expressionClazz 抛出指定异常
     */
    private static <T> void fail(boolean expression, String errorMsg, Class<T> expressionClazz) {
        if (!expression) {
            try {
                Constructor<T> constructor = expressionClazz.getDeclaredConstructor(String.class);
                throw (RuntimeException) constructor.newInstance(errorMsg);
            } catch (
                    NoSuchMethodException
                            | InvocationTargetException
                            | InstantiationException
                            | IllegalAccessException e
            ) {
                e.printStackTrace();
            }
        }
    }
}
