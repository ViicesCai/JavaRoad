package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CAI
 * @time 2021/2/15
 */
@Controller
public class HelloController {

    /**
     *  forward：转发到一个页面
     *  WEB-INF/views/success.jsp：转发到该路径下的success.jsp
     *  一定要加上/，如果不加/就是相对路径；容易出错
     *
     */
    @RequestMapping("/handle01")
    public String handle01() {
        System.out.println("handle01");
        return "forward:/WEB-INF/views/success.jsp";
    }

    /**
     * 支持控制器间转发
     */
    @RequestMapping("/handle02")
    public String handle02() {
        System.out.println("handle02");
        return "forward:/handle01";
    }

    @RequestMapping("/handle03")
    public String handle03() {
        return "redirect:/hello.jsp";
    }

    @RequestMapping("/handle04")
    public String handle04() {
        return "redirect:/handle03";
    }

//    @RequestMapping("/toLogin")
//    public String toLogin() {
//        return "login";
//    }
}
