package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CAI
 * @time 2021/2/22
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {

        return "success";
    }
}
