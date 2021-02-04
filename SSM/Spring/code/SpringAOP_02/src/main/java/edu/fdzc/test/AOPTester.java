package edu.fdzc.test;

import edu.fdzc.inter.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CAI
 * @time 2021/1/13
 */
public class AOPTester {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");

    /**
     * AOP测试
     */
    @Test
    public void test() {
        // 从IOC容器拿到目标对象；注意：如果要使用类型，使用他的接口类型，不要使用本类
        Calculator bean = ioc.getBean(Calculator.class);
        bean.add(1, 2);
        // 获取到的对象是代理对象
        // AOP底层是动态代理，容器中保存的组件的是他的代理对象
        // 目标对象没有实现接口，会自动由CGLib生成一个代理对象:CGLib为没有接口的组件创建代理对象
    }

    /**
     * 通知方法执行顺序
     *
     * try {
     *     @Before
     *     method.invoke(obj, args);
     *     @AfterReturning
     *
     * } catch() {
     *     @AfterThrowing
     *
     * } finally {
     *     @After
     * }
     *
     * 正常执行：@Before(前置通知) -> @After(后置通知) -> @AfterReturning(返回通知)
     * 异常执行：@Before(前置通知) -> @After(后置通知) -> @AfterThrowing(异常通知)
     */
    @Test
    public void test02() {
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(1, 3);
    }

    /**
     * 获取目标方法的详细信息
     */
    @Test
    public void test03() {
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(1, 3);
    }

    /**
     * 环绕通知
     */
    @Test
    public void test04() {
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(1, 3);
    }

    /**
     * 多切面
     */
    @Test
    public void test05() {
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(1, 5);
    }
}
