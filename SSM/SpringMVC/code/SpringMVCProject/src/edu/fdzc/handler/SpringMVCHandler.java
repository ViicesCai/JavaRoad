/**
 * 
 */
package edu.fdzc.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.fdzc.entity.Student;
import edu.fdzc.exception.MyArrayIndexOutOfBoundsException;

/**
 * 控制器类
 * 
 * @author CAI
 *
 */
@Controller // 表示该类是一个控制器
@RequestMapping("handler") 
// 如果在类前添加注解，访问 welcome 则路径映射为SpringMVCHandler/welcome
// 注意 @RequestMapping 注解并不一定要与类名或方法名一致，但是路径映射的地方 一定要对应
// 如: <a href="SpringMVCHandler/welcome">First SpringMVC</a>
@SessionAttributes(value = "student") // 将该对象放入 session 域中
public class SpringMVCHandler {
	// RequestMapping 中也可以定义请求的提交方式，与提交的参数
	@RequestMapping("welcome")
    // 如：@RequestMapping(value="welcome", method=RequestMethod.POST) // 当该请求的提交方式为 Post 才拦截
	// @RequestMapping(value="welcome", method=RequestMethod.POST, params={"name=cai", "age!=22"})
	// 则表示请求必须包含name参数，age!=22 即：age的参数可以是空，或22以外的数
	public String welcome() {
		return "success"; // views/success.jsp：请求转发的跳转方式
	}
	
	@RequestMapping(value="getName/{name}")
	public String getName(@PathVariable("name") String name) {
		System.out.println(name);
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.POST) // 请求校验
	public String testPost(@PathVariable("id") Integer id) {
		System.out.println("post：增  " + id);
		
		// Service 实现增
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.DELETE) // 请求校验
	public String testDelete(@PathVariable("id") Integer id) {
		System.out.println("delete：删 " + id);
		
		// Service 实现删
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.PUT) // 请求校验
	public String testPut(@PathVariable("id") Integer id) {
		System.out.println("put：改 " + id);
		
		// Service 实现改
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.GET) // 请求校验
	public String testGet(@PathVariable("id") Integer id) {
		System.out.println("get：查 " + id);
		
		// Service 实现查
		return "success";
	}
	
	@RequestMapping(value="testParam")
	// 接受前台传递的值：require：必须有值  false(不是必须的)，defaultValue：默认值
	public String testParam(@RequestParam(value = "uname", required = false, defaultValue = "CC") String name) {
		System.out.println(name);
		
		return "success";
	}
	
	@RequestMapping(value="testRequestHeader")
	// 获取请求头消息
	public String testRequestHeader(@RequestHeader("Accept-Language") String val) {
		System.out.println(val);
		
		return "success";
	}
	
	@RequestMapping(value="testCookieValue")
	// 获取 cookie 值
	public String testCookieValue(@CookieValue("JSESSIONID") String jsessionId) {
		System.out.println(jsessionId);
		
		return "success";
	}
	
	@RequestMapping(value="testObjectProperties")
	public String testObjectProperties(Student student) { // Student属性 必须和 form 表单中的属性 name 值一致
		System.out.println(student);
		return "success";
	}
	
	@RequestMapping(value="testServletAPI")
	// 使用 Servlet 的API
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request);
		
		return "success";
	}
	
	@RequestMapping(value="testModelAndView")
	public ModelAndView testModelAndView() { // ModelAndView：既有数据，又有视图
		//Model - M:数据  View - V:视图
		ModelAndView mv = new ModelAndView("modelandview"); // view:success(依然会加上配置的前缀与后缀)
		Student student = new Student();
		student.setId(1);
		student.setName("ss");
		mv.addObject("student", student); // == request.setAttribute() : 所有的数据都放在 request 域中
		
		return mv;
	}
	
	@ModelAttribute // 在任何一次请求前，都会最先执行该注解修饰的方法
	public void queryStudentById(Map<String, Object> map) {
		// 模拟方法
		Student student = new Student();
		student.setId(22);
		student.setName("HH");
		System.out.println(student);
		map.put("stu", student); // 约定：map 的 key 就是方法参数类型的首字母小写
	}
 	
	// 修改 = 查询 + 更新
	@RequestMapping(value="testModelAttribute")
	public String testModelAttribute(@ModelAttribute("stu") Student student) {
		student.setName("恒");
		System.out.println(student);
		
		return "success";
	}
	
	// 国际化响应
	@RequestMapping(value = "testI18n")
	public String testI18n() {
		return "success";
	}
	
	// 测试转换器
	@RequestMapping(value = "testConverter")
	public String testConverter(@RequestParam("studentInfo") Student student) {
		System.out.println(student.toString());
		
		return "success";
	}
	
	// 格式化日期
	@RequestMapping(value = "testDateTimeFormat")
	public String testDateTimeFormat(@Valid Student student, BindingResult result, Map<String, Object> map) { // 如果 Student 格式化出错，会将错误信息传入 result 中
		System.out.println(student.toString() + "," + student.getBirthday());
		
		if (result.getErrorCount() > 0) { // 打印错误信息
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getDefaultMessage());
				map.put("errors", result.getFieldErrors()); // 将错误信息传入 request 域中的 errors
			}
		}
		
		return "test"; 
	}
	
	@ResponseBody // 告诉 SpringMVC 此时返回的不是一个 view 页面 而是一个 ajax 调用的返回值
	@RequestMapping(value = "testJson")
	public List<Student> testJson() {
		// List<Student> students = studentService.queryAllStudent();
		
		// 模拟 service 查询操作
		Student stu1 = new Student(1, "Cai");
		Student stu2 = new Student(2, "Jack");
		Student stu3 = new Student(3, "Kay");
		
		List<Student> students = new ArrayList<Student>();
		students.add(stu1);
		students.add(stu2);
		students.add(stu3);
		
		return students;
	}
	
	// 文件上传
	@RequestMapping(value = "testUpload")
	public String testUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("文件描述信息：" + desc);
		
		String fileName = file.getOriginalFilename(); // 获取上传时的文件名
		
		// jsp 中上传的文件
		InputStream is = file.getInputStream();
		OutputStream os = new FileOutputStream("d:\\" + fileName);
		
		byte[] temp = new byte[1024];
		int len = -1;
		
		while ((len = is.read(temp)) != -1) {
			os.write(temp, 0, len);
		}
		
		os.close();
		is.close();
		
		System.out.println("上传成功！");
		
		return "success";
	}
	
	@RequestMapping("testInterceptor")
	public String testInterceptor() {
		System.out.println("处理请求的方法......");
		
		return "success";
	}
	
	// 数学异常
	@RequestMapping("testExceptionHandler")
	public String testExceptionHandler() {
		System.out.println(1/0);
		
		return "success";
	}
	
	// 数组越界
	@RequestMapping("testExceptionHandler2")
	public String testExceptionHandler2() {
		int[] nums = new int[2];
		System.out.println(nums[2]);
		
		return "success";
	}
	
	// 该方法可以捕获本类中，抛出的 ArithmeticException(数学)或ArrayIndexOutOfBoundsException(数组越界) 异常
	@ExceptionHandler({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})
	public String handlerArithmeticException(Exception e, Model model) {
		System.out.println(e);
		model.addAttribute("error", e);
		
		return "error";
	}
	
	@RequestMapping("testMyException")
	public String testMyException(@RequestParam("i") Integer i) throws MyArrayIndexOutOfBoundsException {
		
		if (i == 3) {
			throw new MyArrayIndexOutOfBoundsException(); // 抛出异常
		}
		
		return "success";
	}
	
	@RequestMapping("testMyException2")
	public String testMyException2(@RequestParam("i") Integer i) {
		
		if (i == 3) {
			return "redirect:testResponseStatus"; // 重定向不会被视图解析器解析
		}
		
		return "success";
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED, reason = "测试自定义异常")
	@RequestMapping("testResponseStatus")
	public String testResponseStatus() {
		
		return "success";
	}
	
	@RequestMapping(value = "welcome2", method = RequestMethod.POST)
	public String welcome2() {
		return "success";
	}
	
	@RequestMapping(value = "testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver() {
		System.out.println(1/0);
		
		return "success";
	}
}
