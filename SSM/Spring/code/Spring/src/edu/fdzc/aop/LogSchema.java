/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import sun.net.www.content.image.jpeg;

/**
 * ͨ�� Schema ʵ��֪ͨ
 * 
 * @author CAI
 *
 */
public class LogSchema {
	
	/**
	 * ǰ��֪ͨ
	 */
	public void before() {
		System.out.println("[Schema-ǰ��֪ͨ]");
	}
	
	/**
	 * ����֪ͨ
	 * 
	 * @param jp
	 * @param returnValue
	 */
	public void afterReturning(JoinPoint jp, Object returnValue) {
		System.out.println("[Schema-����֪ͨ]��Ŀ�����" + jp.getThis() + ",���õķ�������" + jp.getSignature().getName() + ",�����Ĳ���������" + jp.getArgs().length + "�����ķ���ֵ��" + returnValue);

	}
	
	/**
	 * �쳣֪ͨ
	 * 
	 * @param jp
	 * @param e ��ָ���쳣
	 */
	public void whenException(JoinPoint jp, NullPointerException e) {
		System.out.println("[Schema-�쳣֪ͨ]��" + e.getMessage());
	}
	
	/**
	 * ����֪ͨ
	 * 
	 * @param pjp
	 * @return Ŀ�귽���ķ���ֵ
	 */
	public Object around(ProceedingJoinPoint pjp) {
		System.out.println("[Schema-����֪ͨ-ǰ��֪ͨ]");
		Object result = null;
		
		try {
			result = pjp.proceed(); // ִ�з���
			System.out.println("��������" + pjp.getSignature().getName() + ",����ֵ��" + result);
			System.out.println("[Schema-����֪ͨ-����֪ͨ]");
			
		} catch (Throwable e) {
			System.out.println("[Schema-����֪ͨ-�쳣֪ͨ]");
		}

		return result;

	}
}
