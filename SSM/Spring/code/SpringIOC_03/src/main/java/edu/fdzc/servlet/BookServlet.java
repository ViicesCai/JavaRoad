package edu.fdzc.servlet;

import edu.fdzc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author CAI
 * @time 2021/1/12
 */
@Controller
public class BookServlet {
    // 自动装配：自动为该属性赋值
    @Autowired
    private BookService bookService;

    public void doGet() {
        bookService.save();
    }
}
