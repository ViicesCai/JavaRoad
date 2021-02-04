package edu.fdzc.test;

import edu.fdzc.inter.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CAI
 * @time 2021/1/19
 */
public class AOPTester {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
    @Test
    public void test() {
        Calculator calculator = applicationContext.getBean(Calculator.class);
        calculator.add(1,4);
    }
}
