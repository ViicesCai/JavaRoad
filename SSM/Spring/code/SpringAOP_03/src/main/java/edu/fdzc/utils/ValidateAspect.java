package edu.fdzc.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author CAI
 * @time 2021/1/19
 */
public class ValidateAspect {

    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
        Signature signature = joinPoint.getSignature(); // 获取方法签名
        String methodName = signature.getName(); // 获取方法名

        System.out.println("[Validate-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
    }

    public void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[Validate-返回][" + methodName +"] 方法正常执行，结果为：[" + result + "]");
    }

    public void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[Validate-异常][" + methodName + "] 方法执行出现异常，异常信息为：[" + exception + "]");
    }

    public void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[Validate-后置][" + methodName +"] 终止");
    }
}
