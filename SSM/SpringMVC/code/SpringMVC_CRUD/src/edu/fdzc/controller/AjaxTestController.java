package edu.fdzc.controller;

import edu.fdzc.bean.Employee;
import edu.fdzc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.Collection;

/**
 * @author CAI
 * @time 2021/2/19
 */
@Controller
@RequestMapping("/ajax")
public class AjaxTestController {

    @Autowired
    EmployeeDao employeeDao;

    /**
     * 将返回的数据放在响应体中
     * 如果是对象，Jackson：自动将对象转为Json格式
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getallemp")
    public Collection<Employee> getAllEmp() {
        Collection<Employee> all = employeeDao.getAll();

        return all;
    }

    /**
     * @RequestBody：请求体：获取一个请求的请求体
     *      接收json数据，封装为对象
     *
     * @ResponseBody：可以把对象转为json，返回给浏览器
     *
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody Employee employee) {
        System.out.println("请求体：" + employee);

        return "success";
    }

    @RequestMapping("/test01")
    public String test01(@RequestBody String str) {
        System.out.println("请求体：" + str);

        return "success";
    }

    /**
     * 如果使用HttpEntity<String>可以获取请求头的信息
     * 比@RequestBody更强大
     *
     * @param str
     * @return
     */
    @RequestMapping("/test02")
    public String test02(HttpEntity<String> str) {
        System.out.println("请求体：" + str);

        return "success";
    }

    @RequestMapping("/test03")
    public ResponseEntity<String> test03() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        String body = "<h1>success</h1>";

        headers.set("Set-Cookie", "username = CAI");

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    /**
     * SpringMVC文件下载
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
        // 得到要下载的文件的流
        // 获取下载文件的真实路径
        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("/js/jquery.js");
        FileInputStream fileInputStream = new FileInputStream(realPath);

        byte[] temp = new byte[fileInputStream.available()];
        fileInputStream.read(temp);
        fileInputStream.close();

        // 要下载的文件流
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment;filename=" + "jquery.js");
        return  new ResponseEntity<byte[]>(temp, headers, HttpStatus.OK);
    }
}
