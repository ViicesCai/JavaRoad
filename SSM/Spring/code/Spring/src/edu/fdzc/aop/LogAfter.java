/**
 * 
 */
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * ����֪ͨ��
 * 
 * @author CAI
 *
 */
public class LogAfter implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("����֪ͨ��Ŀ�����" + target + ",���õķ�������" + method.getName() + ",�����Ĳ���������" + args.length + "�����ķ���ֵ��" + returnValue);
	}
	
}
