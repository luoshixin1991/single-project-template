package com.boxuegu.crm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 处理错误的工具类
 *
 * @author lsx
 * @date 2021/9/23 14:19
 */
public class ErrorUtil {

    /**
     * 获取异常栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }


}
