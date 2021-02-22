package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CAI
 * @time 2021/2/7
 */
@Controller
public class BookController {

    @RequestMapping(value = "/book/{book_id}", method = RequestMethod.GET)
    public String getBook(@PathVariable("book_id") Integer id) {
        System.out.println("查询到：" + id + "号图书");

        return "success";
    }

    @RequestMapping(value = "/book/{book_id}", method = RequestMethod.POST)
    public String addBook(@PathVariable("book_id") Integer id) {
        System.out.println("新增：" + id + "号图书");

        return "success";
    }

    @RequestMapping(value = "/book/{book_id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("book_id") Integer id) {
        System.out.println("删除：" + id + "号图书");

        return "success";
    }

    @RequestMapping(value = "/book/{book_id}", method = RequestMethod.PUT)
    public String updateBook(@PathVariable("book_id") Integer id) {
        System.out.println("更新：" + id + "号图书");

        return "success";
    }
}
