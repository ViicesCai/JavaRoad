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
@Aspect
@Component
public class LogUtils {

    /**
     * 告诉Spring每个方法何时执行
     *
     * @Before(前置通知)：在目标方法之前执行
     * @After(后置通知)：在目标方法结束之后执行
     * @AfterReturning(返回通知)：在目标方法返回之后执行
     * @AfterThrowing(异常通知)：在目标方法抛出异常之后执行
     * @Around(环绕通知)：在目标方法前后执行
     */


    /**
     * 执行目标方法之前执行：需要写切入点表达式
     *
     * 切入点表达式固定写法：execution(访问权限符 返回值类型 方法全类名(参数表))
     * 通配符：
     * *：
     *   1.匹配一个或多个字符：execution(public int edu.fdzc.impl.Math*r.*(int, int))
     *   2.匹配任意一个参数：execution(public int edu.fdzc.impl.Math*r.*(int, *))：第一个参数为int，第二个参数任意
     *   3.匹配一层路径
     *   4.权限位置不能使用*，不写为默认public
     *
     * ..：
     *   1.匹配任意多个参数，任意类型参数
     *   2.匹配任意多层路径
     *
     * 总结：最模糊的：execution(* *.*(..))：任意包任意类任意方法
     *      最精确的：execution(public int edu.fdzc.impl.MathCalculator.add(int, int))
     *
     * &&：同时满足两个表达式
     *      execution(...) && execution(...)
     *
     * ||：满足任意一个表达式
     *      execution(...) || execution(...)
     *
     * !：只要不是该位置都切入
     *      !execution(...)
     *
     *
     * 在通知方法运行时，得到目标方法的详细信息
     * 为通知方法的参数列表写一个参数：JoinPoint joinPoint：封装了当前目标方法的详细信息
     *
     * Spring对通知方法的要求不严格：
     * 唯一要求：参数列表不能乱写：通知方法是利用反射调用的，故每次调用需要确定参数表的值
     */

    /**
     * 抽取可重用的切入点表达式：
     *  1.随意声明一个没有实现的返回void空方法
     *  2.为该方法表注：@Pointcut注解
     */
    @Pointcut("execution(public int edu.fdzc.impl.MathCalculator.*(..))")
    public void myPoint() { }

    // execution(方法签名) *：所有方法
    @Before("myPoint()")
    public static void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
        Signature signature = joinPoint.getSignature(); // 获取方法签名
        String methodName = signature.getName(); // 获取方法名

        System.out.println("[LogUtils-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
    }

    /**
     * 目标运行正常执行完成后执行
     *
     * returning = result：告诉Spring使用result接收返回值
     */
    @AfterReturning(value = "myPoint()", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-返回][" + methodName +"] 方法正常执行，结果为：[" + result + "]");
    }

    /**
     * 目标方法出现异常时执行
     *
     * throwing = "exception"：告诉Spring使用exception接收异常信息
     */
    @AfterThrowing(value = "myPoint()", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-异常][" + methodName + "] 方法执行出现异常，异常信息为：[" + exception + "]");
    }

    /**
     * 目标方法结束时执行
     */
    @After("myPoint()")
    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-后置][" + methodName +"] 终止");
    }

    /**
     * @Around：环绕：是Spring中强大的通知
     * @Around：环绕：动态代理
     *
     * try {
     *     // 前置通知
     *     method.invoke(obj, args);
     *     // 返回通知
     *
     * } catch(e) {
     *     // 异常通知
     *
     * } finally {
     *     // 后置通知
     * }
     *
     * 四合一为环绕通知
     * 环绕通知优先于普通通知执行：
     * [普通前置]
     *
     * {
     *     try {
     *         [环绕前置]
     *         [环绕执行]：目标方法执行
     *         [环绕返回]
     *
     *     } catch() {
     *         [环绕异常]
     *
     *     } finally {
     *         [环绕后置]
     *     }
     * }
     * [普通后置]
     * [普通方法返回/异常]
     *
     * 即：(环绕前置/普通前置[随机]) -> 目标方法执行 -> 环绕返回/异常 -> 环绕后置 -> 普通后置 -> 普通返回/异常
     */
    //@Around("myPoint()")
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
