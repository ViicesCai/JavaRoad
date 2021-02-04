package edu.fdzc.test;

import edu.fdzc.servlet.BookServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Spring的单元测试
 * 1.需要导入spring-test.jar
 * 2.@ContextConfiguration(locations="")：使用它来指定Spring的配置文件的位置
 * 3.@RunWith：指定用哪种驱动进行单元测试，默认为JUnit
 *      -@RunWith(SpringJUnit4ClassRunner.class):使用Spring的单元测试模块执行@Test的方法
 *
 * 好处：不需要通过ioc.getBean()获取组件；直接使用@AutoWired自动装配
 *
 * @author CAI
 * @time 2021/1/13
 */
@ContextConfiguration(locations = "classpath:conf/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    ApplicationContext ioc;

    @Autowired
    BookServlet bookServlet;

    @Test
    public void test() {
        System.out.println(bookServlet);
    }
}
