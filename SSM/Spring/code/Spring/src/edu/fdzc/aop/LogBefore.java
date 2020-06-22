/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * ǰ��֪ͨ��
 * 
 * @author CAI
 *
 */
public class LogBefore implements MethodBeforeAdvice {

	/**
	 * ǰ��֪ͨ�ľ�������
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws Throwable
	 */
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("ǰ��֪ͨ");
	}
}
