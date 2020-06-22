/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置通知类
 * 
 * @author CAI
 *
 */
public class LogBefore implements MethodBeforeAdvice {

	/**
	 * 前置通知的具体内容
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws Throwable
	 */
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("前置通知");
	}
}
