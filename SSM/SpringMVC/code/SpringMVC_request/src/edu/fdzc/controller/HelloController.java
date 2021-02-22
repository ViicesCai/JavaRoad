package edu.fdzc.controller;

import edu.fdzc.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author CAI
 * @time 2021/2/13
 */
@Controller
public class HelloController {

    /**
     * SpringMVC 获取请求携带的信息
     * 默认方法：
     *      直接给方法的入参写一个与请求参数名相同的变量，此变量可以接收请求参数的值
     *      例：<a href="handle?username=CAI">handle</a>
     *          String username
     *      带参数：有值；不带参数：null
     *
     * @RequestParam：获取请求参数；参数默认是必带的
     *      若不存在该参数则报错
     *      例：@RequestParam("username") String username
     *      value：指定要获取的参数的key
     *      required：此参数是否是必须的
     *      defaultValue：默认值；默认为null
     *
     *      与 @PathVariable 的区别：
     *      PathVariable是获取路径上的参数，不能获取请求参数
     *      RequestParam是获取请求参数，不能获取路径上的参数
     *
     * @RequestHeader：获取请求头中某个key的值
     *      如果请求头中不存在该Key，则报错
     *      例：@RequestHeader("User-Agent") String userAgent
     *      value：指定要获取的参数的key
     *      required：此参数是否是必须的
     *      defaultValue：默认值；默认为null
     *
     * @CookieValue：获取某个cookie的值
     *      不存在该值则报错
     *      例：@CookieValue("JSESSIONID") String sessionID
     *      value：指定要获取的参数的key
     *      required：此参数是否是必须的
     *      defaultValue：默认值；默认为null
     */
    @RequestMapping(value = "handle")
    public String handle(@RequestParam(value = "username", required = false, defaultValue = "不存在") String username) {
        System.out.println("获取的请求参数：" + username);

        return "success";
    }

    @RequestMapping(value = "handle2")
    public String handle2(@RequestHeader("User-Agent") String userAgent) {
        System.out.println("获取的请求头：" + userAgent);

        return "success";
    }

    @RequestMapping(value = "handle3")
    public String handle3(@CookieValue("JSESSIONID") String sessionID) {
        System.out.println("Cookie的值：" + sessionID);

        return "success";
    }

    /**
     * 如果请求参数是一个POJO：
     *  SpringMVC会自动的为这个POJO类赋值？
     *      1.将POJO中的每一个属性，从request参数中取出，并封装
     *      2.还可以封装级联属性，即：属性的属性
     *      3.请求参数的参数名和对象中的属性必须一一对应
     *
     *  提交的数据存在中文乱码的问题：
     *  请求乱码：
     *          GET请求：修改：server.xml
     *              在8080端口处添加：URIEncoding="UTF-8"
     *
     *          POST请求：
     *              在第一次获取请求参数之前设置
     *               response.setContentType("text/html;charset=utf-8")
     *
     *              或自己写一个filter：
     *               SpringMVC已经定义了，在 web.xml 中配置即可
     *
     */
    @RequestMapping("/book")
    public String addBook(Book book) {
        System.out.println("添加的图书信息：" + book);

        return "success";
    }

    /**
     * SpringMVC可以直接在参数上写原生API
     *
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     *
     * java.security.Principal
     * Locale：与国际化相关的区域信息
     * InputStream：
     *      ServletInputSteam inputStream = request.getInputStream()
     * OutputStream：
     *      ServletOutputStream outputStream = response.getOutputStream()
     * Reader：
     *      BufferedReader reader =  request.getReader()
     * Writer
     *      PrintWriter writer = response.getWriter()
     */
    @RequestMapping("/handle4")
    public String handle4(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) throws IOException {
        request.setAttribute("reqParam", "请求域中的属性");
        session.setAttribute("sessionParam", "Session域中的属性");
        PrintWriter writer = response.getWriter();

        return "success";
    }
}
