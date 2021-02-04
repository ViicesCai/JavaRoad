package edu.fdzc.test;

import edu.fdzc.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author CAI
 * @time 2021/1/20
 */
public class Tester {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/tx.xml");

    @Test
    public void test() throws SQLException {
        // 有事务的业务逻辑，容器中保存的是这个业务逻辑的代理对象
        BookService bookService = ioc.getBean(BookService.class);
        bookService.checkout("Tom", "ISBN-001");
        System.out.println("结账完成");
    }
}
