package fdzc.test;

import fdzc.beans.Car;
import fdzc.beans.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author CAI
 * @time 2021/1/7
 */
public class IOCTester {
    ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");

    /**
     * 单例Bean生命周期
     * (容器启动)构造器 -> 初始化方法 -> (容器关闭)销毁方法
     *
     * 多实例Bean生命周期
     * 获取bean -> 初始化方法 -> 容器关闭不会调用销毁方法
     *
     * 后置处理器的生命周期
     * (容器启动)构造器 -> 后置处理器before ->初始化方法 -> 后置处理器after -> (容器关闭)销毁方法
     *
     * 无论bean是否有初始化方法；后置处理器都会默认其有，还会继续工作
     */
    @Test
    public void test() {
        System.out.println("容器关闭");
        ioc.close();
    }

    ApplicationContext ioc2 = new ClassPathXmlApplicationContext("conf/applicationContext02.xml");

    /**
     * Spring管理连接池引用外部文件
     *
     * @throws SQLException
     */
    @Test
    public void test02() throws SQLException {
        // 从IOC容器中获得连接池
        // DataSource dataSource = (DataSource) ioc.getBean("dataSource");

        // 按照类型获取组件，可以获取到这个类型下的所有实现子类
        DataSource dataSource = ioc2.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());

        Car bean = ioc2.getBean(Car.class);
        System.out.println(bean);
    }

    ApplicationContext ioc3 = new ClassPathXmlApplicationContext("conf/applicationContext03.xml");

    /**
     * 基于XML的自动装配：自定义类型自动赋值
     */
    @Test
    public void test03() {
        Person person = (Person) ioc3.getBean("person");
        System.out.println(person);
    }

    /**
     * SpEL
     */
    @Test
    public void test04() {
        Person person = (Person) ioc3.getBean("person02");
        System.out.println(person);
    }
}
