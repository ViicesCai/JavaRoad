package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.告诉SpringMVC这是一个控制器，可以处理请求
 * @Controller：标识哪个组件是控制器
 *
 * @author CAI
 * @time 2021/2/5
 */
@Controller
public class MyController {

    /**
     * 处理请求
     * @RequestMapping：请求映射
     * /代表从当前项目下开始，即处理当前项目下的hello请求
     */
    @RequestMapping("/hello")
    public String firstRequest() {
        System.out.println("处理成功！!");

        // 配置视图解析器后自动拼接：即 prefix + 返回值 + suffix
        return "success";
    }
}
