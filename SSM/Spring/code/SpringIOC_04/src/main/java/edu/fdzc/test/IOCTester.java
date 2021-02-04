package edu.fdzc.test;

import edu.fdzc.service.BookService;
import edu.fdzc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author CAI
 * @time 2021/1/13
 */
@ContextConfiguration(locations = "classpath:conf/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTester {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Test
    public void test() {
        bookService.save();
        userService.save();
    }
}
