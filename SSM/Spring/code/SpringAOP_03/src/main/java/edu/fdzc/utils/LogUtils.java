package edu.fdzc.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志工具类（切面类）
 * AOP：如何将这个类的方法（通知方法）动态的在目标方法运行的各个位置切入
 *
 * @author CAI
 * @time 2021/1/13
 */

public class LogUtils {

    public static void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
        Signature signature = joinPoint.getSignature(); // 获取方法签名
        String methodName = signature.getName(); // 获取方法名

        System.out.println("[LogUtils-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
    }

    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-返回][" + methodName +"] 方法正常执行，结果为：[" + result + "]");
    }

    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-异常][" + methodName + "] 方法执行出现异常，异常信息为：[" + exception + "]");
    }

    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-后置][" + methodName +"] 终止");
    }

    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs(); // 获取参数列表
        String methodName = proceedingJoinPoint.getSignature().getName(); // 获取方法名

        // 利用反射调用目标方法：即method.invoke(obj, args)
        Object proceed = null;

        try {
            System.out.println("[LogUtils][环绕前置通知] [" + methodName + "方法开始]");
            proceed = proceedingJoinPoint.proceed(args);
            System.out.println("[LogUtils][环绕返回通知] [" + methodName + "方法返回，返回值" + proceed + "]");

        } catch (Exception e) {
            System.out.println("[LogUtils][环绕异常通知] [" + methodName + "] 方法出现异常，异常信息：" + e);

            // 为了让外界感知到异常，需要抛出
            throw new RuntimeException(e);
        } finally {
            System.out.println("[LogUtils][环绕后置通知] [" + methodName + "] 最终结束");
        }

        // 反射调用后的返回值也一定返回出去
        return proceed;
    }
}
