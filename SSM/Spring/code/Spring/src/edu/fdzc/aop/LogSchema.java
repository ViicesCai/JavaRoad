/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import sun.net.www.content.image.jpeg;

/**
 * 通过 Schema 实现通知
 * 
 * @author CAI
 *
 */
public class LogSchema {
	
	/**
	 * 前置通知
	 */
	public void before() {
		System.out.println("[Schema-前置通知]");
	}
	
	/**
	 * 后置通知
	 * 
	 * @param jp
	 * @param returnValue
	 */
	public void afterReturning(JoinPoint jp, Object returnValue) {
		System.out.println("[Schema-后置通知]：目标对象：" + jp.getThis() + ",调用的方法名：" + jp.getSignature().getName() + ",方法的参数个数：" + jp.getArgs().length + "方法的返回值：" + returnValue);

	}
	
	/**
	 * 异常通知
	 * 
	 * @param jp
	 * @param e 空指针异常
	 */
	public void whenException(JoinPoint jp, NullPointerException e) {
		System.out.println("[Schema-异常通知]：" + e.getMessage());
	}
	
	/**
	 * 环绕通知
	 * 
	 * @param pjp
	 * @return 目标方法的返回值
	 */
	public Object around(ProceedingJoinPoint pjp) {
		System.out.println("[Schema-环绕通知-前置通知]");
		Object result = null;
		
		try {
			result = pjp.proceed(); // 执行方法
			System.out.println("方法名：" + pjp.getSignature().getName() + ",返回值：" + result);
			System.out.println("[Schema-环绕通知-后置通知]");
			
		} catch (Throwable e) {
			System.out.println("[Schema-环绕通知-异常通知]");
		}

		return result;

	}
}
