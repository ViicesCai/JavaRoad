package edu.fdzc.controller;

import edu.fdzc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CAI
 * @time 2021/2/22
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController() {
        System.out.println("BookController...");
    }

    @RequestMapping("/book")
    public String hello() {
        System.out.println(bookService);

        return "forward:/index.jsp";
    }
}
