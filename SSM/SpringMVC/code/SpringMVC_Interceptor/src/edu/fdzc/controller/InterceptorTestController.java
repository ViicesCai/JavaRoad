package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CAI
 * @time 2021/2/21
 */
@Controller
public class InterceptorTestController {

    // 拦截流程：拦截器的preHandle -> 目标方法 -> 拦截器的postHandle -> 页面 -> 拦截器的afterCompletion
    // preHandle不放行：返回 false，则没有之后流程
    // 只要放行：afterCompletion都会执行
    @RequestMapping("/test01")
    public String test01() {
        System.out.println("test01 running...");

        return "success";
    }
}
