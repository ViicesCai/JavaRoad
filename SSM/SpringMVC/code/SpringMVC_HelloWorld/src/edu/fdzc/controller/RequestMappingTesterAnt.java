package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @RequestMapping 支持模糊匹配功能
 *
 * URL中可以写模糊的通配符：
 *  ?：能替代任意一个字符
 *  *：能替代任意多个字符，或一层路径
 *  **：能替代多层路径
 *
 * @author CAI
 * @time 2021/2/6
 */
@Controller
@RequestMapping("/ant")
public class RequestMappingTesterAnt {

    @RequestMapping("/request01?")
    public String antRequest01() {
        System.out.println("ant - request01? - 请求成功！");

        return "success";
    }

    @RequestMapping("/request02")
    public String antRequest02() {
        System.out.println("ant - request02 - 请求成功！");

        return "success";
    }

    @RequestMapping("/*/request03")
    public String antRequest03() {
        System.out.println("ant - request03 - 请求成功！");

        return "success";
    }

    @RequestMapping("/**/request04")
    public String antRequest04() {
        System.out.println("ant - request04 - 请求成功！");

        return "success";
    }

    // 路径上可以有占位符，通过在任意处写上{变量名}：路径上的占位符只能是一层路径
    // 通过(@PathVariable("变量名") 可以获取该占位符的赋值
    @RequestMapping("/pathVariableRequest/{id}")
    public String pathVariableRequest(@PathVariable("id") String id) {
        System.out.println("ant - PathVariableRequest - 占位符的值：" + id);

        return "success";
    }
}
