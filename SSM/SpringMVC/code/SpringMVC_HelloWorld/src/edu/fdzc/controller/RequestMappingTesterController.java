package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 在类上添加@RequestMapping
 *
 * @author CAI
 * @time 2021/2/5
 */
@RequestMapping("/controller")
@Controller
public class RequestMappingTesterController {

    // @RequestMapping 第一个属性：value，默认，规定请求的URL
    @RequestMapping("/request")
    public String request() {
        System.out.println("RequestMappingTesterController - request - 请求成功！");

        return "success";
    }

    // @RequestMapping 第二个属性：method，规定请求方式
    // method = RequestMethod.POST：表示只接受该种类型的请求，默认全接收
    @RequestMapping(value = "/request02", method = RequestMethod.POST)
    public String request02() {
        System.out.println("RequestMappingTesterController - request02 - 请求成功！");

        return "success";
    }

    // params = {"username=cai"}：必须包含username=cai这个参数
    @RequestMapping(value = "/request03", params = {"username=cai"})
    public String request03() {
        System.out.println("RequestMappingTesterController - request03 - 请求成功！");

        return "success";
    }


    @RequestMapping(value = "/request04", headers = {"User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:85.0) Gecko/20100101 Firefox/85.0"})
    public String request04() {
        System.out.println("RequestMappingTesterController - request04 - 请求成功！");

        return "success";
    }
}
