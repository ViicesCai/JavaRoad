package edu.fdzc.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志工具类
 *
 * @author CAI
 * @time 2021/1/13
 */
public class LogUtils {

    /**
     * 日志开始执行
     *
     * @param method 方法
     * @param args 参数数组
     */
    public static void logStart(Method method, Object... args) {
        System.out.println("[" + method.getName() + "] 方法开始执行，参数列表为：" + Arrays.toString(args));

    }

    /**
     * 日志执行结束
     *
     * @param method 方法
     * @param result 返回对象
     */
    public static void logReturn(Method method, Object result) {
        System.out.println("[" + method.getName() + "] 方法正常执行，结果为：" + result);
    }

    /**
     * 日志执行异常
     *
     * @param method 方法
     * @param e 异常
     */
    public static void logException(Method method, Exception e) {
        System.out.println("[" + method.getName() + "] 方法执行出现异常，异常信息为：" + e.getCause());
    }

    /**
     * 日志终止
     *
     * @param method 方法
     */
    public static void logEnd(Method method) {
        System.out.println("[" + method.getName() + "] 终止");
    }
}
