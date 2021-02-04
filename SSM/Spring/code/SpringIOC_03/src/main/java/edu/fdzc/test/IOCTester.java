package edu.fdzc.test;

import edu.fdzc.dao.BookDao;
import edu.fdzc.service.BookService;
import edu.fdzc.servlet.BookServlet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CAI
 * @time 2021/1/12
 */
public class IOCTester {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");

    /**
     * 通过注解将组件添加到IOC容器
     * 使用注解或XML配置加入到容器中的组件行为都是默认一样的
     * 组件的id：默认就是组件的类名首字母小写
     *      修改注解可以更改id：@Repository("bookdao")：即可通过"bookdao"获取
     *
     * 组件的作用域：默认为单例
     *      使用@Scope(value="prototype")：将组件修改为多实例
     */
    @Test
    public void test() {
        Object bean = ioc.getBean("bookDao");
        Object bean2 = ioc.getBean("bookDao");

        System.out.println(bean == bean2);
    }

}
