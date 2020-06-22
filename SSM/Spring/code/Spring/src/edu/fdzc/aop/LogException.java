/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/**
 * �쳣֪ͨ��
 * 
 * @author CAI
 *
 */
public class LogException implements ThrowsAdvice {
	
	/**
	 * �쳣֪ͨ�ľ��巽��
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Throwable ex) {
		System.out.println("�쳣֪ͨ��Ŀ�����" + target + ",��������" + method.getName() + ",�����Ĳ�����" + args.length + ",�쳣���ͣ�" + ex.getMessage());
	}
}
