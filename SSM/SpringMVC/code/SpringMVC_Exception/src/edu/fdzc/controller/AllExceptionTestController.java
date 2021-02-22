package edu.fdzc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 集中处理所有异常
 *  1.集中处理所有异常的类加入到ioc容器中
 *  2.@ControllerAdvice：专门处理异常的类
 *
 * @author CAI
 * @time 2021/2/22
 */
@ControllerAdvice
public class AllExceptionTestController {

//    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
//    public ModelAndView handleException01(Exception e) {
//        System.out.println("全局的handleException01" + e);
//
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("ex", e);
//
//        return modelAndView;
//    }

//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView handleException02(Exception e) {
//        System.out.println("全局的handleException02" + e);
//
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("ex", e);
//
//        return modelAndView;
//    }
}
