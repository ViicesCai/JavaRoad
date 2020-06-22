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
 * ͨ��ע��ʵ��ǰ��֪ͨ
 * 
 * @author CAI
 *
 */
@Component("logAnnotation") // ���뵽 bean ��
@Aspect // ����������һ��֪ͨ
public class LogAspectAnnotation {

	/**
	 * ǰ��֪ͨ����
	 */
	@Before(value = "execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(..))") // ���ԣ����������
	public void before(JoinPoint jp) { // JoinPoint:��ȡĿ�����
		System.out.println("[ע��---ǰ��֪ͨ]��Ŀ�����" + jp.getTarget() + ",��������" + jp.getSignature().getName() + 
				",�����б�" + Arrays.toString(jp.getArgs()));
	}
	
	/**
	 * ����֪ͨ��������д�����Ļ�����ΧΪ���а��Ĵ˷�����
	 */
	@AfterReturning(value = "execution(public * addStudent(..))", returning = "returningValue")
	public void afterReturning(JoinPoint jp, Object returningValue) { // JoinPoint:��ȡĿ�����; returingValue:����ֵ
		System.out.println("[ע��---����֪ͨ]��Ŀ�����" + jp.getTarget() + ",��������" + jp.getSignature().getName() + 
				",�����б�" + Arrays.toString(jp.getArgs()) + ",����ֵ��" + returningValue);
	}
	
	/**
	 * ����֪ͨ����
	 */
	@Around("execution(public * addStudent(..))")
	public void around(ProceedingJoinPoint pjp) {
		// ǰ��֪ͨ
		System.out.println("[ע��---����֪ͨ---ǰ��֪ͨ]");
		
		try {
			// ִ�з���
			Object result = pjp.proceed();
			
			// ����֪ͨ
			System.out.println("[ע��---����֪ͨ---����֪ͨ]");

		} catch (Throwable e) {
			// �쳣֪ͨ
			System.out.println("[ע��---����֪ͨ---�쳣֪ͨ]");

		} finally {
			// ����֪ͨ
			System.out.println("[ע��---����֪ͨ---����֪ͨ]");
		}
	}
	
	/**
	 * �쳣֪ͨ����
	 */
	@AfterThrowing(pointcut = "execution(public * addStudent(..))", throwing = "e")
	public void exception(JoinPoint jp, NullPointerException e) { // ֻ�����ض����͵��쳣����ָ�룩
		System.out.println("[ע��---�쳣֪ͨ]" + e.getMessage());
	}
	
	/**
	 * ����֪ͨ����
	 */
	@After("execution(public * addStudent(..))")
	public void after() {
		System.out.println("[ע��---����֪ͨ]");
	}
}
