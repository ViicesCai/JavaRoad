package edu.fdzc.proxy;

import edu.fdzc.inter.Calculator;
import edu.fdzc.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 运算动态代理类
 *
 * @author CAI
 * @time 2021/1/13
 */
public class CalculatorProxy {

    /**
     * 为传入的对象创建一个动态代理对象
     *
     * @param calculator 被代理的对象
     * @return 动态代理对象
     */
    public static Calculator getProxy(final Calculator calculator) {
        InvocationHandler handler = new InvocationHandler() {

            /**
             * 方法执行器接口
             *
             * @param proxy 代理对象
             * @param method 将要执行的目标对象的方法
             * @param args 传入的参数值
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null; // 返回结果

                try {
                    LogUtils.logStart(method, args);
                    // 利用反射执行目标方法
                    res = method.invoke(calculator, args);
                    LogUtils.logReturn(method, res);

                } catch (Exception e) {
                    LogUtils.logException(method, e);

                } finally {
                    LogUtils.logEnd(method);
                }

                return res;
            }
        }; // 目标方法执行器

        Class<?>[] interdaces = calculator.getClass().getInterfaces(); // 对象实现的所有接口
        ClassLoader loader = calculator.getClass().getClassLoader(); // 被代理对象的类加载器

        // 为目标对象创建代理对象
        Object proxy = Proxy.newProxyInstance(loader, interdaces, handler);
        return (Calculator) proxy;
    }
}
