package edu.fdzc.controller;

import edu.fdzc.exception.UserNameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author CAI
 * @time 2021/2/22
 */
@Controller
public class ExceptionTestController {

    @RequestMapping("/handle01")
    public String handle01(Integer i) {
        System.out.println("handle01......");
        System.out.println(10/i);

        return "success";
    }

    @RequestMapping("/handle02")
    public String handle02(@RequestParam("username") String username) {
        if (!username.equals("admin")) {
            System.out.println("登录失败");

            throw new UserNameNotFoundException();
        }

        System.out.println("登录成功");

        return "success";
    }

    @RequestMapping(value = "/handle03", method = RequestMethod.POST)
    public String handle03() {

        return "success";
    }

    @RequestMapping("/handle04")
    public String handle04() {
        String str = null;
        System.out.println(str.charAt(0));

        return "success";
    }

    /**
     * @ExceptionHandler：告诉SpringMVC，该方法专门处理该类发生的异常：可在value指定处理的异常
     *  1.该方法可以使用Exception用于接收发生的异常
     *  2.要携带的异常信息不能用Model传递
     *  3.直接返回 ModelAndView 即可
     *  4.如果存在多个 @ExceptionHandler 能处理异常，越精确的越优先
     *  5.全局异常处理与本类异常处理同时存在，本类优先
     */
//    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
//    public ModelAndView handleException01(Exception e) {
//        System.out.println("本类的handleException01" + e);
//
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("ex", e);
//
//        return modelAndView;
//    }
}
