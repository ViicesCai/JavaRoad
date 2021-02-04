package edu.fdzc.factory;

import edu.fdzc.beans.Book;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * 实现FactoryBean接口的类为Spring的工厂类
 * Spring会自动调用工厂方法创建的实例
 *
 * @author CAI
 * @time 2021/1/7
 */
public class MyFactoryBeanImple implements FactoryBean<Book> {

    /**
     * 工厂方法：返回创建对象：Spring自动调用
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("FactoryBeanImple创建对象");
        Book book = new Book();
        book.setBookName(UUID.randomUUID().toString());
        return book;
    }

    /**
     * 工厂方法：返回创建的对象的类型：Spring自动调用该方法来确认创建对象的类型
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * 返回的对象是否是单例：false：不是，true：是
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
