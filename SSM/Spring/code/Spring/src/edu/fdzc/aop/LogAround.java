/**
 * 
 */
package edu.fdzc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕通知类
 * 
 * @author CAI
 *
 */
public class LogAround implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		try {
			// invocation.proceed() 之前的代码：前置通知
			System.out.println("用环绕通知实现前置通知");
			Object result = invocation.proceed(); // 控制目标方法的执行：接受该方法的返回值
			// invocation.proceed() 之后的代码：后置通知
			System.out.println("用环绕通知实现后置通知");
			System.out.println("目标对象：" + invocation.getThis() + ",调用方法名：" + invocation.getMethod().getName() + ",方法的参数个数：" + invocation.getArguments() + ",方法的返回值：" + result);

		} catch (Exception e) {
			// 异常通知
			System.out.println("用环绕通知实现异常通知");
		}
		
		return null;
	}

}
