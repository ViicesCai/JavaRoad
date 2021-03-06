# 	SpringMVC

> spring-webmvc.jar
>
> spring-aop.jar
>
> spring-bean.jar
>
> spring-context.jar
>
> spring-core.jar
>
> spring-expression.jar
>
> commons-logging.jar
>
> spring-web.jar

> SpringMVC 对应 Servlet

## 配置

> SpringMVC 配置文件 = springmvc.xml

> 选择命名空间：beans、aop、context、mvc

> 普通的 Servlet 流程：
>
> 请求 -> url-pattern -> 对应的 Servlet
>
> 如何让 MVC 介入程序？需配置 SpringMVC 自带的 Servlet

``` xml
<!-- web.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
  <display-name>SpringMVCProject</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
  <servlet>
  	<servlet-name>springDispatcherServlet</servlet-name>
  	<!-- 将请求交由 MVC 处理 -->
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 如果该文件(springDispatcherServlet-servlet.xml)在 WEB-INF 下则可以省略 -->
  	<init-param>
  		<!-- 指定 SpringMVC 配置文件 -->
  		<param-name>contextConfigLocation</param-name>
  		<param-value>classpath:springmvc.xml</param-value>
  	</init-param>
  	<!-- 启动时，以第一个自动启动 -->
  	<load-on-startup>1</load-on-startup>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>springDispatcherServlet</servlet-name>
  	<!-- 拦截一切请求 -->
  	<url-pattern>/</url-pattern>
    <!-- url-pattern：拦截的请求 -->
    <!-- /user: 拦截以 /user 开头的请求 如：user/welcome -->
    <!-- /user/welcome.jsp: 只拦截该请求 -->
    <!-- .jsp: 只拦截 .jsp 结尾的请求 -->
    <!-- 如果定义的请求这里没有拦截到，则不属于 SpringMVC 处理：@RuestMapping 映射，将自动交由 Servlet 处理：url-parttern / @WebServlet -->
  </servlet-mapping>
</web-app>
```

> SpringMVC 类包名规范
>
> xxx.xxx.servlet = xxx.xxx.controller = xxx.xxx.handler = xxx.xxx.action => 泛指一个控制器类

``` java
// 例子

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器类
 * 
 * @author CAI
 *
 */
@Controller // 表示该类是一个控制器
// @RequestMapping("SpringMVCHandler") 
// 如果在类前添加注解，访问 welcome 则路径映射为SpringMVCHandler/welcome
// 注意 @RequestMapping 注解并不一定要与类名或方法名一致，但是路径映射的地方 一定要对应
// 如: <a href="SpringMVCHandler/welcome">First SpringMVC</a>
public class SpringMVCHandler {

	@RequestMapping("welcome") // RequestMapping 中也可以定义请求的提交方式
    // 如：@RequestMapping(value="welcome", method=RequestMethod.POST) // 当该请求的提交方式为 Post 才拦截
	public String welcome() {
		return "success"; // views/success.jsp
	}
}
```

> 在 WEB-INF 下创建 views 文件夹，并添加 success.jsp
>
> 配置 springmvc.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">

	<!-- 扫描包：通常使用了注解定义 必须使用扫描获取 -->
	<context:component-scan base-package="edu.fdzc.handler"></context:component-scan>
	
	<!-- 配置视图解析器(InternalResourceViewResolver) -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 前缀 -->
		<property name="prefix" value="/views/"></property>
		<!-- 后缀 -->
		<property name="suffix" value=".jsp"></property>
        <!-- 例：注意与方法的返回和目录对应：前缀即表示：WEB-INF/views -->
        <!-- 后缀则对应的是：success.jsp -->
	</bean>
</beans>
```

> index.jsp

``` jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="welcome">First SpringMVC</a>
</body>
</html>
```

> 点击 welcome 自动跳转至 success.jsp 页面

> // 配置请求的请求头参数：即下图的参数这里不具体举例说明
>
> @RequestMapping(value="", header={"Accept="})

![image-20200625094602067](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200625094602067.png)

### ant 风格请求路径

+ ？：单字符
+ *：任意个字符（0或多个）
+ **：任意目录

``` java
@RequestMapping("welcome/*/test")：可以通过 a href = welcome/hellowold/test 访问
@RequestMapping("welcome/**/test")：可以通过 a href = welcome/hello/wold/test 访问：任意层子目录
@RequestMapping("welcome/o?o/test")：可以通过 welcome/owo/test 访问
```

### 获取动态参数

> @PathVariable

``` java
@RequestMapping(value="getName/{name}")
public String getName(@PathVariable("name") String name) {
	System.out.println(name);
	return "success";
}
```

## 快速配置

> 在 eclipse 中可以使用快捷键生成之前写的配置文件：必须安装 Spring 插件

> 在 web.xml ：alt + /

![image-20200625103927117](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200625103927117.png)

## RESTFUL

> 软件编程风格
>
> SpringMVC 中的提交方式
>
> GET:查
>
> POST:增
>
> DELETE:删
>
> PUT:改
>
> 普通浏览器只支持 GET、POST 方式：其他请求可以通过 filter 过滤器支持
>
> 约定：过滤的请求
>
> <input type="hidden" name="_method" value="delete/put">
>
> 请求方式为 post，当该条件满足时，将 post 变更为 delete 或 put

### 配置

> SpringMVC 提供了该过滤器,只需要进行配置即可

``` xml
<!-- 增加：HiddenHttpMethodFilte 过滤器：使普通浏览器支持 delete/put -->
<filter>  
	<filter-name>HiddenHttpMethodFilter</filter-name>
  	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>

<filter-mapping>
  	<filter-name>HiddenHttpMethodFilter</filter-name>
  	<url-pattern>/</url-pattern>
</filter-mapping>
```

``` jsp
<!-- index.jsp -->
<form action="handler/testRest/1234" method="post">
	<input type="submit" value="增">
</form>
<br/>
	
<!-- 普通浏览器不支持 Delete/Put 必须使用如下定义 过滤器才能获取 -->
<!-- 提交方式必须为 POST; 通过隐藏域设置实际的请求方式：DELETE/PUT -->
<form action="handler/testRest/1234" method="post">
	<input type="hidden" name="_method" value="DELETE">
	<input type="submit" value="删">
</form>
<br/>
	
<form action="handler/testRest/1234" method="post">
	<input type="hidden" name="_method" value="PUT">
	<input type="submit" value="改">
</form>
<br/>
	
<form action="handler/testRest/1234" method="get">
	<input type="submit" value="查">
</form>
```

``` java
/**
 * 控制器类
 * 
 * @author CAI
 *
 */
@Controller // 表示该类是一个控制器
@RequestMapping("handler") 
public class SpringMVCHandler {

	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.POST) // 请求校验
	public String testPost(@PathVariable("id") Integer id) {
		System.out.println("post：增  " + id);
		
		// Service 实现增
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.DELETE) // 请求校验：匹配具体的请求方式
	public String testDelete(@PathVariable("id") Integer id) {
		System.out.println("delete：删 " + id);
		
		// Service 实现删
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.PUT) // 请求校验：匹配具体的请求方式
	public String testPut(@PathVariable("id") Integer id) {
		System.out.println("put：改 " + id);
		
		// Service 实现改
		return "success";
	}
	
	@RequestMapping(value="testRest/{id}", method=RequestMethod.GET) // 请求校验：匹配具体的请求方式
	public String testGet(@PathVariable("id") Integer id) {
		System.out.println("get：查 " + id);
		
		// Service 实现查
		return "success";
	}
} // @RequestMapping 允许同名：当其同名时可以通过 Method 处理不同的请求
```

> 注意：如果点击删除与修改出现 405：在 success.jsp 头部添加 isErrorPage="true"：开启错误文件处理

> 请求过滤器的实现原理

``` java
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

    HttpServletRequest requestToUse = request; // 原始请求 request只支持：get、post、header
    
    // 如果是 post 且有隐藏域则将过滤器的原始请求 request 加入新的请求方式
    if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
        String paramValue = request.getParameter(this.methodParam);
        
		if (StringUtils.hasLength(paramValue)) {
            requestToUse = new HttpMethodRequestWrapper(request, paramValue);
			}
		}
    // 最后将 该请求放入请求链中，实际使用的就是改造之后的请求
    filterChain.doFilter(requestToUse, response);
}
```

## 接收参数

> @RequestParam

``` java
@RequestMapping(value="testParam")
// 接受前台传递的值：require：必须有值  false(不是必须的)，defaultValue：默认值
public String testParam(@RequestParam(value = "uname", required = false, defaultValue = "CC") String name) {
	System.out.println(name);
		
	return "success";
}
```

> @RequestHeader

``` java
@RequestMapping(value="testRequestHeader")
// 获取请求头消息
public String testRequestHeader(@RequestHeader("Accept-Language") String val) {
	System.out.println(val);
		
	return "success";
}
```

> @CookieValue

``` java
@RequestMapping(value="testCookieValue")
// 获取 cookie 值
public String testCookieValue(@CookieValue("JSESSIONID") String jsessionId) {
	System.out.println(jsessionId);
		
	return "success";
}
```

> SpringMVC 处理各种参数的流程/逻辑
>
> 请求：前端发出请求 a -> @RequestMapping("a")
>
> 处理请求中的参数：通过注解 获取各种参数：@RequestParam 前台参数....等等

> 使用对象（实体类）接收参数

``` jsp
<form action="handler/testObjectProperties" method="post">
    id:<input name="id" type="text">
	name:<input name="name" type="text">
	homeaddress:<input name="address.homeAddress" type="text">
	schooladdress:<input name="address.schoolAddress" type="text">
	<input type="submit" value="提交">
</form>
```

``` java
@RequestMapping(value="testObjectProperties")
public String testObjectProperties(Student student) { // Student属性 必须和 form 表单中的属性 name 值一致
	System.out.println(student);
	return "success";
}
```

> 在 SpringMVC 使用原生的 Servlet API

``` java
@RequestMapping(value="testServletAPI")
// 使用 Servlet 的API
public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
	System.out.println(request);
		
	return "success";
}
```

## 处理模型数据

> 如果跳转时需要带数据：使用以下方式

``` java
@RequestMapping(value="testModelAndView")
// 方式1
public ModelAndView testModelAndView() { // ModelAndView：既有数据，又有视图
	//Model - M:数据  View - V:视图
	ModelAndView mv = new ModelAndView("modelandview"); // view:success(依然会加上配置的前缀与后缀)
	Student student = new Student();
	student.setId(1);
	student.setName("ss");
	mv.addObject("student", student); // == request.setAttribute() : 所有的数据都放在 request 域中
		
	return mv;
}

// 方式2
public ModelAndView testModelAndView(ModelMap modelMap) {
	Student student = new Student();
	student.setId(1);
	student.setName("ss");
    
    modelMap.put("student", student); // == request.setAttribute() : 所有的数据都放在 request 域中
		
	return mv;
}

// 方式3
public ModelAndView testModelAndView(Map<String, Object> map) {
	Student student = new Student();
	student.setId(1);
	student.setName("ss");
    
    map.put("student", student); // == request.setAttribute() : 所有的数据都放在 request 域中
		
	return mv;
}

// 方式4
public ModelAndView testModelAndView(Model model) {
	Student student = new Student();
	student.setId(1);
	student.setName("ss");
    
    model.addAttribute("student", student); // == request.setAttribute() : 所有的数据都放在 request 域中
		
	return mv;
}
```

``` jsp
<a href="handler/testModelAndView">ModelAndView</a>
```

``` java
@SessionAttributes(value="student") // 将该对象放入 session 域中
// @SessionAttributes(types=Student.class) // 将学生类型的数据放入 session 域中 
@RequestMapping(value="handler")
public class SpringMVCHandler {
	
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
}
```

``` jsp
----Request<br/>
${requestScope.student.id} - ${requestScope.student.name}<br/>
	
----Session<br/>
${sessionScope.student.id} - ${sessionScope.student.name}<br/>
```

> 修改域中的数据

``` java
// 修改 = 查询 + 更新
@ModelAttribute // 在任何一次请求前，都会最先执行该注解修饰的方法
public void queryStudentById(Map<String, Object> map) {
	// 模拟方法
	Student student = new Student();
	student.setId(22);
	student.setName("HH");
	System.out.println(student);
	map.put("student", student); // 约定：map 的 key 就是方法参数类型的首字母小写
}
 	
// 修改 = 查询 + 更新
@RequestMapping(value="testModelAttribute")
public String testModelAttribute(Student student) {
	student.setName("恒");
	System.out.println(student);
		
	return "success";
}
```

``` java
// 如果 map 的 key 与方法参数类的命名不一致
map.put("stu", student);

@RequestMapping(value="testModelAttribute")
public String testModelAttribute(@ModelAttribute("stu") Student student) {
	student.setName("恒");
	System.out.println(student);
		
	return "success";
}
```

``` jsp
<form action="handler/testModelAttribute" method="post">
	编号:<input name="id" type="hidden" value="30"> <br/>
	姓名:<input name="name" type="text" > <br/>
	<input type="submit" value="修改">
</form>
```

> @ModelAttribute 经常在更新时使用
>
> 通过 @ModelAttribute 修饰的方法，会在每次请求前先执行
>
> 并且该方法的参数 map.put() 可以将对象放入即将查询的参数中
>
> 必须满足约定：
>
> map.put(k, v) 其中的 k 必须是即将查询的方法参数的首字母小写：
>
> testModelAttribute(Student student) => student
>
> 且当不一致时：使用 @ModelAttribute 声明

## 视图类型

![image-20200629085115574](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200629085115574.png)

> 视图顶级接口：View
>
> 视图解析器顶级接口：ViewResolver

> JstlView extends InternalResourceView
>
> SpringMVC 解析 jsp 时，会默认使用 InternalResourceView，如果发现 JSP 中包含了 jstl 语言，则自动转为 JstlView

### 国际化

> JstlView 可以解析 jstl\实现国际化操作
>
> 国际化：针对不同的国家、不同的地区进行不同的显示
>

> 创建资源文件
>
> 基名\_语言\_地区.properties
>
> 基名\_语言.properties
>
> 基名.properties：默认，如果请求的相应文件不存在，将使用此资源文件
>
> 基名对应配置文件中的 value，语言和地区的简写不能错误，否则无法生效

``` properties
# i18n.properties ,i18n = internationalization(国际化)
# 如果其他资源文件中没有设置一些属性的值，则在该文件中查找
```

``` properties
# i18n_en_US
resource.welcome=WELCOME
resource.exit=EXIT
```

``` properties
# i18n_zh_CN
resource.welcome=欢迎
resource.exit=退出
```

> 配置 springmvc.xml 加载资源文件

``` xml
<!-- 加载国际化资源文件 -->
<bean id="" class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="i18n"></property>
    <!-- ResourceBundleMessageSource 会在 springmvc 响应程序时介入（解析国际化资源文件） -->
</bean>
```

> 需要的 jar 包
>
> jstl.jar
>
> standard.jar

> 使用

``` jsp
<a href="testI18n">国际化</a>
```

``` java
// 国际化响应
@RequestMapping(value = "testI18n")
public String testI18n() {
	return "success";
}
```

``` jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 实现国际化 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:message key="resource.welcome"></fmt:message>
	<fmt:message key="resource.exit"></fmt:message>
	<br>
	Hello World!
</body>
</html>
```

### MVC 处理视图

> 目前：index.jsp -> Controller(@RequsetMapping) -> success.jsp
>
> 目的：使用 MVC 省略 Controller ，index.jsp -> success.jsp

``` xml
<!-- testMvcViewController -> test.jsp -->
<mvc:view-controller path="handler/testMvcViewController" view-name="test"/>
<!-- 会将所有请求转入 <mvc:...> 中匹配映射地址，而会忽略 @RequestMapping() -->
<!-- 此配置是 SpringMVC 的基础配置：使 <mvc...> 和  @RequestMapping() 共存 -->
<mvc:annotation-driven></mvc:annotation-driven>
```

### 跳转方式

``` java
@RequestMapping(value = "test")
ublic String test() {
	return "success"; // 默认为请求转发
    // 指定跳转方式
    // return "forward:/views/success.jsp" 请求转发
    // return "redirect:/views/success.jsp" 重定向
}
```

### 处理静态资源

> html、css、js、图片、视频

> 如果有 SpringMVC 对应的 @RequestMapping 则交给 Spring 处理，如果没有对应的 @RequestMapping 则交给服务器 tomcat 默认的 Servlet 去处理
>
> 实现：在 springmvc.xml 中增加：\<mvc:default-servlet-handler\> 

> 在 SpringMVC 中若直接访问静态资源：404，原因：之前将所有的请求通过通配符 "\\" 拦截交给 SpringMVC 的入口 springDispatcherServlet 进行处理：找到该请求对应的 @RequestMapping

#### 配置

> 如果需要 mvc 处理则交由 @RequestMapping("...png")处理，如不需要 springmvc处理则使用 tomcat 默认的 Servlet 去处理：如果有对应的请求拦截，则交由相应的 Servlet 去处理，如果没有对应的 Servlet 则直接访问

``` xml
<!-- 默认Servlet ：tomcat配置文件\conf\web.xml -->
 <servlet>
     <servlet-name>default</servlet-name>
     <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
     <init-param>
         <param-name>debug</param-name>
         <param-value>0</param-value>
     </init-param>
     <init-param>
         <param-name>listings</param-name>
         <param-value>false</param-value>
     </init-param>
     <load-on-startup>1</load-on-startup>
</servlet>

<!-- The mapping for the default servlet -->
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

> 在 springmvc.xml 中配置

``` xml
<!-- 该配置让 SpringMVC: 接收一个请求，并且该请求没有对应的 @RequestMapping时，将该请求交给服务器默认的 Servlet 去处理（直接访问） -->
<mvc:default-servlet-handler/>

<!-- 使 SpringMVC 既能访问静态资源又能访问动态资源 -->
<mvc:annotation-driven />
```

### 类型转换

> Spring 自带一些常见的类型转换器：(@PathVariable("id") Integer id)

#### 自定义类型转换器

> 创建转换器

``` java
import org.springframework.core.convert.converter.Converter;

import edu.fdzc.entity.Student;

/**
 * 自定义类型转换器
 * 
 * @author CAI
 *
 */
public class MyConverter implements Converter<String, Student> {

	public Student convert(String source) {
		// 接受前端传来的字符串
		String[] studentStrArr = source.split("-");
		Student student = new Student();
		student.setId(Integer.parseInt(studentStrArr[0]));
		student.setName(studentStrArr[1]);
		
		return student;
	}
}
```

> 配置 springmvc.xml

``` xml
<!-- 将自定义转换器纳入 SpringIOC 容器 -->
<bean id="myConverter" class="edu.fdzc.converter.MyConverter"></bean>
	
<!-- 将 myConverter 纳入 SpringMVC 提供的转换器 bean 中 -->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
	<property name="converters">
		<set>
			<ref bean="myConverter"/>
		</set>
	</property>
</bean>
	
<!-- 将 conversionService 注册到 annotation-driven 中 -->
<!-- 此配置是 SpringMVC 的基础配置，很多功能需要此配置进行协调 -->
<mvc:annotation-driven  conversion-service="conversionService"/>
```

> 使用

``` xml
<form action="handler/testConverter" method="post">
	学生信息：<input name="studentInfo" type="text"> <br>
	<input type="submit" value="转换"> <br>
</form>
```

``` java
// 测试转换器
@RequestMapping(value = "testConverter")
// @RequestParam("studentInfo") 接收前端传递过来的学生信息，将数据复制给修饰的目的对象 Student；因此 SpringMVC 可以发现接收的数据和目标数据不一致，因此会触发转换器
public String testConverter(@RequestParam("studentInfo") Student student) {
	System.out.println(student.toString());
		
	return "success";
}
```

## 数据格式化

> SpringMVC 通过注解实现数据格式化

``` xml
<!-- 
	配置数据格式化注解所依赖的 bean 
	FormattingConversionServiceFactoryBean：既可以实现数据格式化、又可以实现类型转换
-->
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
	<property name="converters">
		<set>
			<ref bean="myConverter"/>
		</set>
	</property>
</bean>
```

> 注解

``` java
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * 学生类
 * 
 * @author CAI
 *
 */
public class Student {
	@NumberFormat(pattern = "###,#") // 格式化
	private int id;
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 格式化：前台传递来的数据，将前台传递来的数据固定为 yyyy-MM-dd
	private Date birthday;
	
	...... 省略
}
```

> 控制器

``` java
// 格式化日期
@RequestMapping(value = "testDateTimeFormat")
public String testDateTimeFormat(Student student, BindingResult result) { // 如果 Student 格式化出错，会将错误信息传入 result 中
    System.out.println(student.toString() + "," + student.getBirthday());
		
    if (result.getErrorCount() > 0) { // 打印错误信息
		for (FieldError error : result.getFieldErrors()) {
			System.out.println(error.getDefaultMessage());
		}
	}
		
	return "success";
}
```

> 测试

``` jsp
<form action="handler/testDateTimeFormat" method="post">
	编号：<input name="id" type="hidden" value="31"> <br>
	姓名：<input name="name" type="text"> <br>
	出生日期：<input name="birthday" type="text"> <br>
	<input type="submit" value="提交"> <br>
</form>
```

### 错误消息处理

``` java
// 格式化日期
@RequestMapping(value = "testDateTimeFormat")
public String testDateTimeFormat(Student student, BindingResult result, Map<String, Object> map) { // 如果 Student 格式化出错，会将错误信息传入 result 中
	System.out.println(student.toString() + "," + student.getBirthday());
		
	if (result.getErrorCount() > 0) { // 打印错误信息
		for (FieldError error : result.getFieldErrors()) {
			System.out.println(error.getDefaultMessage());
			map.put("errors", result.getFieldErrors()); // 将错误信息传入 request 域中的 errors
		}
	}
		
	return "test"; 
}
```

``` jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<c:forEach items="${requestScope.errors}" var="error">
		${error.getDefaultMessage()}
	</c:forEach>
</body>
</html>
```

> public String testDateTimeFormat(Student student, BindingResult result, Map<String, Object> map)
>
> 需要验证的数据是 Student 中添加注解的属性，如果校验失败的话则将错误信息放入 BindingResult 
>
> 即：Student 与 BindingResult 之间不允许存在其他参数
>
> 如果要将控制台的错误消息传到 jsp 中显示，则可以将错误消息放入 request 域中，然后在 jsp 中从 request 域中获取

### 数据校验

#### JSR 303

| 注解                        |                          详细信息                          |
| --------------------------- | :--------------------------------------------------------: |
| @Null                       |                  被注释的元素必须为 null                   |
| @NotNull                    |                 被注释的元素必须不为 null                  |
| @AssertTrue                 |                  被注释的元素必须为 true                   |
| @AssertFalse                |                  被注释的元素必须为 false                  |
| @Min(value)                 | 被注释的元素必须是一个数字，其值必须大于或等于指定的最小值 |
| @Max(value)                 | 被注释的元素必须是一个数字，其值必须小于或等于指定的最大值 |
| @DecimalMin(value)          | 被注释的元素必须是一个数字，其值必须大于或等于指定的最小值 |
| @DecimalMax(value)          | 被注释的元素必须是一个数字，其值必须小于或等于指定的最大值 |
| @Size(max, min)             |            被注释的元素的大小必须在指定的范围内            |
| @Digits (integer, fraction) |    被注释的元素必须是一个数字，其值必须在可接受的范围内    |
| @Past                       |              被注释的元素必须是一个过去的日期              |
| @Future                     |              被注释的元素必须是一个将来的日期              |
| @Pattern(value)             |            被注释的元素必须符合指定的正则表达式            |

#### Hibernate Validator

> JSR 303 的拓展

| 注解      |                详细信息                |
| --------- | :------------------------------------: |
| @Email    |     被注释的元素必须是电子邮箱地址     |
| @Length   | 被注释的字符串的大小必须在指定的范围内 |
| @NotEmpty |        被注释的字符串的必须非空        |
| @Range    |     被注释的元素必须在合适的范围内     |

#### 配置

> hibernate-validator.jar
>
> classmate.jar
>
> jboss-logging.jar
>
> validatetion-api.jar
>
> hibernate-validator-annotation-processor.jar

> 实现数据校验必须实现 ValidatorFactory 接口
>
> LocalValidatorFactoryBean 是 ValidatorFactory 的一个实现类

+ \<mvc:annotation-driven\>\</mvc:annotation-driven\> 会在 SpringMVC 容器中自动加载该实现类

#### 使用

``` java
// 在类中使用注解
public class Student {
    @Past // 当前时间以前
    private Date birthday;
    ...
}
```

``` java
// 控制器中添加 @Valid
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
```

## AJAX 请求 SpringMVC

> jackson-annotations.jar
>
> jackson-core.jar
>
> jackson-databind.jar

+ @ResponseBody 修饰的方法：将该方法的返回值以一个 json 数组的形式返回给前台

``` java
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
```

``` jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
    // 服务端将返回值结果以 json 数组的形式传给 result
	$(document).ready(function(){
		$("#testJson").click(function(){
			// 通过 Ajax 请求 SpringMVC
			$.post(
				"handler/testJson", // 服务器地址
				// {"name" : "cai"}, // 传递参数

				function(result){ // 服务端处理之后的回调函数：返回 List<Student>
					for(var i = 0; i < result.length; i++){
						alert(result[i].id +  " - " + result[i].name);
					}
				}
			);
		});
	});
</script>
</head>
<body>
	<input type="button" value="testJson" id="testJson" /> <br/>
</body>
</html>
```

## 文件上传

> commons-fileupload.jar
>
> commons-io.jar

+ 必须实现 MultipartResolver 接口，SpringMVC 提供实现类：CommonsMultipartResolver 类 实现上传

``` xml
<!-- 配置CommonsMultipartResolver：用于实现文件上传，将其加入 SpringIOC 容器 -->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	<!-- 上传文件的默认编码 -->
	<property name="defaultEncoding" value="UTF-8" />
	<!-- 上传单个文件的最大容量，单位 byte：-1 表示无限制 -->
	<property name="maxUploadSize" value="102400" />	
</bean>
```

``` jsp
<form action="handler/testUpload" method="post" enctype="multipart/form-data">
	<input type="file" name="file" /> <br>
	描述：<input name="desc" type="text" /> <br>
	<input type="submit" value="上传" />
</form>
```

``` java
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
```

> 使用框架实现文件上传大大缩减了代码量，但是必须得清楚具体的实现配置

## 拦截器

> 等同于过滤器
>
> 必须实现：HandlerInterceptor 接口

![image-20200713105040549](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200713105040549.png)

``` java
/**
 * 拦截器
 * 
 * @author CAI
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截请求");
		
		return true; // true：拦截操作之后放行；false：拦截之后不放行，请求终止
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("拦截响应");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("视图渲染完毕");
	}
}
```

``` xml
<!-- 将拦截器配置到 SpringMVC 中：默认拦截全部 -->
<mvc:interceptors>		
    <!-- 配置具体的拦截路径 -->
	<mvc:interceptor>
		<!-- 指定拦截的路径，基于 ant 风格 -->
		<mvc:mapping path="/**"/>
			<!-- 指定不拦截的路径 -->
			<mvc:exclude-mapping path="/handler/testUpload"/>
			<bean class="edu.fdzc.interceptor.MyInterceptor" />
		</mvc:interceptor>
</mvc:interceptors>
```

> 若配置多个拦截器，则：
>
> 拦截器1拦截请求 -> 拦截器2拦截请求 -> 请求方法 -> 拦截器2处理响应 -> 拦截器1处理响应 -> 只会被最后一个拦截器渲染（即：拦截器2）

## 异常处理

> 要实现异常处理必须实现 HandlerExceptionResolver 接口
>
> 该接口的每个实现类，都是异常的一种处理方式

### ExceptionHandlerExceptionResolver 类

> 该类提供 @ExceptionHandler 注解：通过该注解处理异常

``` java
// 该方法可以捕获本类中，抛出的 ArithmeticException(数学)或ArrayIndexOutOfBoundsException(数组越界) 异常
@ExceptionHandler({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})
public String handlerArithmeticException(Exception e, Model model) {
	System.out.println(e);
	model.addAttribute("error", e);
		
	return "error";
}
```

> @ExceptionHandler 标识的方法参数必须在异常类型（Throwable或其子类），不能包含其他类型的参数

> 异常处理路径：最短优先：即 Exception 优先级最高

> 若发生异常的方法和处理异常的方法不再同一类中：则需要使用 @ControllerAdvice

``` java
/**
 * 异常处理类
 * 
 * @author CAI
 *
 */
@ControllerAdvice
public class MyExceptionHandler {

	// 可以处理任何类中的异常
	@ExceptionHandler({Exception.class})
	public String handlerArithmeticException(Exception e, Model model) {
		System.out.println(e);
		model.addAttribute("error", e);
			
		return "error";
	}
}
```

### ResponseStatusExceptionResolver 类

> 自定义异常显示页面：@ResponseStatus

``` java
/**
 * 处理数组越界异常
 * 
 * @author CAI
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "数组越界异常") // 状态码-状态描述
public class MyArrayIndexOutOfBoundsException extends Exception { // 自定义异常
    
}

```

``` java
@RequestMapping("testMyException")
	public String testMyException(@RequestParam("i") Integer i) throws MyArrayIndexOutOfBoundsException {
		
		if (i == 3) {
			throw new MyArrayIndexOutOfBoundsException(); // 抛出异常
		}
		
		return "success";
	}
```

> 方式2

``` java
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
```

### DefaultHandlerExceptionResolver 类

> 默认的异常处理类

``` java
@RequestMapping(value = "welcome2", method = RequestMethod.POST)
public String welcome2() {
	return "success";
}
```

> 此时通过 get 方式访问该控制器则自动报错：405

### SimpleMappingExceptionResolver 类

> 通过配置实现异常的处理

``` java
@RequestMapping(value = "testSimpleMappingExceptionResolver")
public String testSimpleMappingExceptionResolver() {
	System.out.println(1/0);
		
	return "success";
}
```

``` xml
<!-- SimpleMappingExceptionResolver:以配置方式，处理异常 -->
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
	<!-- 将该异常保存到 requestScope中，通过 error 获取 -->
    <!-- 未配置该属性：默认为 exception -->
	<property name="exceptionAttribute" value="error"/>
	<property name="exceptionMappings">
		<props>
			<!-- 等同于：catch(ArithmeticException e) -->
			<prop key="java.lang.ArithmeticException">
				error <!-- 捕获异常后：跳转到 error 页面 -->
			</prop>
		</props>
	</property>
</bean>
```

## SSM 整合

> Spring - SpringMVC - MyBatis

### Spring - MyBatis

> 将 MyBatis 的 SqlSessionFactory 交给 Spring

查看：Spring 的 项目整合

### Spring - SpringMVC

> 将 SpringMVC 加入项目

> spring-webmvc.jar

#### 配置

``` xml
<!-- web.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
  <display-name>SSMProject</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
  <!-- web项目中，引入 Spring -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	
	<!-- WEB项目 整合 SpringMVC -->
	<servlet>
		<servlet-name>springDispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:applicationContext-controller.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>springDispatcherServlet</servlet-name>
		<!-- 拦截一切请求 -->
		<url-pattern>/</url-pattern>
	</servlet-mapping>
</web-app>
```

``` xml
<!--SpringMVC配置文件：applicationContext-controller.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">
    
	<!-- 配置视图解析器 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/views/"/>
		<property name="suffix" value=".jsp"/>
	</bean>
	
	<!-- SpringMVC 基础配置 -->
	<mvc:annotation-driven></mvc:annotation-driven>
</beans>
```

### 示例

参照 code 文件夹/SSMProject

