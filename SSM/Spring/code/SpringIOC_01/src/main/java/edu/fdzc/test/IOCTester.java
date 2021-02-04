package edu.fdzc.test;

import edu.fdzc.beans.Book;
import edu.fdzc.beans.Car;
import edu.fdzc.beans.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author CAI
 * @time 2021/1/3
 */
public class IOCTester {

    /**
     * 细节：
     * 1. ApplicationContext(IOC容器接口)
     * 2. 给容器中注册一个组件：组件的创建工作由容器完成
     *    容器中的对象在容器被创建时都创建了
     * 3. 同一个组件在IOC容器中都是单例的，仅会被创建一次
     * 4. 若容器中不存在某个组件，尝试获取该组件时报异常
     *    org.springframework.beans.factory.NoSuchBeanDefinitionException
     * 5. IOC容器在创建这个组件对象时，property会利用setter方法为javaBean的属性进行赋值
     * 6. javaBean的属性由getter/setter的方法决定的
     *    所有的getter/setter建议都由编译器自动生成
     */


    /**
     * 通过IOC容器创建对象，并为属性赋值
     */
    @Test
    public void test01() {
        // ApplicationContext：代表ioc容器
        // ClassPathXmlApplicationContext：当前应用的xml配置文件在ClassPath下
        // 根据Spring的配置文件得到IOC容器对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        // 使用容器创建的对象
        Object bean = (Person) ioc.getBean("person01");

        System.out.println(bean);
    }

    ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");

    /**
     * 根据Bean的类型从IOC容器中获取Bean的实例
     * 此种方式获取Bean，只允许拥有一个此种类型的Bean，否则报此异常
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type
     */
    @Test
    public void test02 () {
        // Person bean = ioc.getBean(Person.class);
        Person bean = ioc.getBean("person01", Person.class);

        System.out.println(bean);
    }

    /**
     * 通过构造函数为Bean的属性赋值
     */
    @Test
    public void test03() {
        Object bean = ioc.getBean("person02");
        System.out.println(bean);

        Object bean2 = ioc.getBean("person03");
        System.out.println(bean2);

        Object bean3 = ioc.getBean("person04");
        System.out.println(bean3);

        Object bean4= ioc.getBean("person05");
        System.out.println(bean4);

    }

    /**
     * 通过p标签为属性赋值
     */
    @Test
    public void test04() {
        Object bean = ioc.getBean("person06");
        System.out.println(bean);
    }

    ApplicationContext ioc2 = new ClassPathXmlApplicationContext("ioc2.xml");
    /**
     * 正确的为各种属性赋值
     */
    @Test
    public void test05() {
        Person bean = (Person) ioc2.getBean("person01");
        Car car = bean.getCar();

        System.out.println(car);

        List<Book> books = bean.getBooks();
        System.out.println(books);
        // 注意：内部bean不能通过id获取

        Map map = bean.getMap();
        System.out.println(map);

        Properties prop = bean.getProperties();
        System.out.println(prop);
    }

    /**
     * 正确的为各种属性赋值
     */
    @Test
    public void test06() {
        Person person = (Person) ioc2.getBean("person02");
        Map map = person.getMap();

        System.out.println(map);

        Map<String, Object> map1 = (Map<String, Object>) ioc2.getBean("myMap");
        System.out.println(map1.getClass());
    }

    /**
     * 级联属性
     */
    @Test
    public void test07() {
        Person person = (Person) ioc2.getBean("person03");
        Object car = ioc2.getBean("car01");
        System.out.println("容器中的Car：" + car);
        System.out.println("Person中的Car：" + person.getCar());

        // 级联属性：属性的属性，在操作时，引用的是该属性，所有修改级联属性时，其引用的bean也修改了
    }

    /**
     * 通过继承实现bean配置信息的重用
     */
    @Test
    public void test08() {
        Person person = (Person) ioc2.getBean("person05");
        System.out.println(person);
    }

    ApplicationContext ioc3 = new ClassPathXmlApplicationContext("ioc3.xml");

    /**
     * Bean 之间的依赖
     * 观察ioc3.xml的配置顺序发现：创建Bean是按照这个顺序来的
     */
    @Test
    public void test09() {
        System.out.println("容器已启动");
    }

    /**
     * 通过静态工厂创建bean
     */
    @Test
    public void test10() {
        Object bean = ioc3.getBean("airPlane01");
        System.out.println(bean);
    }

    /**
     * 通过实例工厂创建bean
     */
    @Test
    public void test11() {
        Object bean = ioc3.getBean("airPlane02");
        System.out.println(bean);
    }

    /**
     * 通过FactoryBean工厂创建对象
     */
    @Test
    public void test12() {
        Object bean = ioc3.getBean("myFactoryBeanImple");
        System.out.println(bean);
    }
}
