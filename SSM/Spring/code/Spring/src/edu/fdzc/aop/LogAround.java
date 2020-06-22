/**
 * 
 */
package edu.fdzc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * ����֪ͨ��
 * 
 * @author CAI
 *
 */
public class LogAround implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		try {
			// invocation.proceed() ֮ǰ�Ĵ��룺ǰ��֪ͨ
			System.out.println("�û���֪ͨʵ��ǰ��֪ͨ");
			Object result = invocation.proceed(); // ����Ŀ�귽����ִ�У����ܸ÷����ķ���ֵ
			// invocation.proceed() ֮��Ĵ��룺����֪ͨ
			System.out.println("�û���֪ͨʵ�ֺ���֪ͨ");
			System.out.println("Ŀ�����" + invocation.getThis() + ",���÷�������" + invocation.getMethod().getName() + ",�����Ĳ���������" + invocation.getArguments() + ",�����ķ���ֵ��" + result);

		} catch (Exception e) {
			// �쳣֪ͨ
			System.out.println("�û���֪ͨʵ���쳣֪ͨ");
		}
		
		return null;
	}

}
