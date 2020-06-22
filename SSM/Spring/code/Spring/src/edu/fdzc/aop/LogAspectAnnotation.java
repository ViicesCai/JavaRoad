/**
 * 
 */
package edu.fdzc.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 通过注解实现前置通知
 * 
 * @author CAI
 *
 */
@Component("logAnnotation") // 加入到 bean 中
@Aspect // 声明该类是一个通知
public class LogAspectAnnotation {

	/**
	 * 前置通知方法
	 */
	@Before(value = "execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(..))") // 属性：定义切入点
	public void before(JoinPoint jp) { // JoinPoint:获取目标对象
		System.out.println("[注解---前置通知]：目标对象：" + jp.getTarget() + ",方法名：" + jp.getSignature().getName() + 
				",参数列表：" + Arrays.toString(jp.getArgs()));
	}
	
	/**
	 * 后置通知方法（不写包名的话，范围为所有包的此方法）
	 */
	@AfterReturning(value = "execution(public * addStudent(..))", returning = "returningValue")
	public void afterReturning(JoinPoint jp, Object returningValue) { // JoinPoint:获取目标对象; returingValue:返回值
		System.out.println("[注解---后置通知]：目标对象：" + jp.getTarget() + ",方法名：" + jp.getSignature().getName() + 
				",参数列表：" + Arrays.toString(jp.getArgs()) + ",返回值：" + returningValue);
	}
	
	/**
	 * 环绕通知方法
	 */
	@Around("execution(public * addStudent(..))")
	public void around(ProceedingJoinPoint pjp) {
		// 前置通知
		System.out.println("[注解---环绕通知---前置通知]");
		
		try {
			// 执行方法
			Object result = pjp.proceed();
			
			// 后置通知
			System.out.println("[注解---环绕通知---后置通知]");

		} catch (Throwable e) {
			// 异常通知
			System.out.println("[注解---环绕通知---异常通知]");

		} finally {
			// 最终通知
			System.out.println("[注解---环绕通知---最终通知]");
		}
	}
	
	/**
	 * 异常通知方法
	 */
	@AfterThrowing(pointcut = "execution(public * addStudent(..))", throwing = "e")
	public void exception(JoinPoint jp, NullPointerException e) { // 只捕获特定类型的异常（空指针）
		System.out.println("[注解---异常通知]" + e.getMessage());
	}
	
	/**
	 * 最终通知方法
	 */
	@After("execution(public * addStudent(..))")
	public void after() {
		System.out.println("[注解---最终通知]");
	}
}
