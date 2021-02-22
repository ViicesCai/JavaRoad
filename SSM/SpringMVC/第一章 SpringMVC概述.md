# 第一章 SpringMVC概述

## MVC

> 常用的软件架构模式

+ M：`Model模型`：封装和映射数据`(JavaBean)`
+ V：`View视图`：界面显示工作`(.jsp)`
+ C：`Controller控制器`：控制整个网站的跳转逻辑`(Servlet)`

### 流程图

![image-20210204233209356](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210204233209356.png)

> MVC提倡：每一层只编写自己的东西，不写任何其他的代码
>
> 分层的目的就是为了解耦，解耦使维护方便也有利于分工合作

## 概述

1. `Spring `为展现层提供的基于` MVC 设计理念`的优秀的 `Web 框架`，是目前最主流的 `MVC 框架`之一
2. `Spring MVC `通过一套 `MVC `注解，让` POJO `成为处理请求的控制器，而无须实现任何接口
3. 支持` REST `风格的 `URL `请求
4. 采用了松散耦合可插拔组件结构，比其他 `MVC 框架`更具扩展性和灵活性

### 请求处理流程

![image-20210205104210861](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210205104210861.png)

1. 首先客户端发送请求到前端控制器`DispatcherServlet`，前端控制器根据请求信息（如：URL）来决定选择用哪个页面控制器`Controller`来处理，并把请求委托给它，即`Servlet`控制器的控制逻辑部分（步骤：1 -> 2）

2. 页面控制器接收到请求后，进行业务处理，处理完毕后返回一个`ModelAndView(模型数据和逻辑视图名)`

   （步骤：3 -> 4 -> 5）

3. 前端控制器收回控制权，然后根据返回的逻辑视图名选择相应的真正视图，并把模型数据传入以便将视图渲染展示

   （步骤：6 -> 7）

4. 前端控制器再次收回控制权，将响应结果返回给用户，至此整个流程结束（步骤：8）

## 常用组件

1. `DispatcherServlet`：前端控制器

2. `Controller`：处理器/页面控制器，做的是MVC中的C的事情，但控制逻辑转移到前端控制器了，用于对请求进行处理

3. `HandlerMapping`：请求映射到处理器，找谁来处理，如果映射成功返回一个`HandlerExecutiongChain`对象（包含一个`Handler处理器`(页面控制器)对象、多个`HandlerInterceptor`拦截器对象）

4. `ViewResolver` ： 视图解析器，找谁来处理返回的页面

   把逻辑视图解析为具体的`View`，进行这种策略模式，很容易更换其他视图技术

   + 如`InternalResourceViewResolver`将逻辑视图名映射为`JSP`视图

5. LocalResolver：本地化、国际化

6. `MultipartResolver`：文件上传解析器

7. `HandlerExceptionResolver`：异常处理器

## 创建HelloWorld

> 使用IDEA自动创建

> 需要的JAR包

1. 核心容器模块

   + spring-aop.jar

   + spring-bean.jar

   + spring-context.jar

   + spring-core.jar

   + spring-expression.jar

   + commons-logging.jar

2. WEB模块

   + `spring-webmvc.jar`
   + `spring-web.jar`

### 配置

> 在`web.xml`编写配置
>
> 指定`SpringMVC`配置文件的位置

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- web项目参数配置 -->
    <context-param>
        <!-- 整个web项目的配置文件位置 -->
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:conf/applicationContext.xml</param-value>
    </context-param>
    <!-- 监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!--
        SpringMVC：有一个前端控制器能拦截所有请求，并智能派发
        该前端控制器是一个Servlet需要在web.xml中进行配置
        默认的Servlet是在收到请求后创建的，前端控制器在服务器启动时就创建了
    -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 前端控制器的初始化参数 -->
        <init-param>
            <!-- 指定文件配置位置 -->
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:conf/dispatcher-servlet.xml</param-value>
        </init-param>
        <!-- Servlet启动加载：load-on-startup：服务器启动时创建，值越小越优先 -->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 拦截所有请求 -->
        <!--
            /与/*的区别：
            /*：范围更大，会拦截到如：*.jsp的请求：这样会导致页面无法显示
            /：会拦截所有请求，但不会拦截*.jsp
        -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

> `SpringMVC`的配置文件：`dispatcher-servlet.xml`

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 扫描所有组件 -->
    <context:component-scan base-package="edu.fdzc"/>

    <!-- 配置视图解析器：帮我们拼接页面地址 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

> 控制器

``` java
package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.告诉SpringMVC这是一个控制器，可以处理请求
 * @Controller：标识哪个组件是控制器
 *
 *
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
        System.out.println("处理成功！!!");

        // 配置视图解析器后自动拼接：即 prefix + 返回值 + suffix
        return "success";
    }
}
```

> 页面

``` jsp
<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/5
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <a href="hello">Hello</a>
  </body>
</html>
```

### 运行流程

1. 客户端点击：`http://localhost:8080/SpringMVC_HelloWorld_war_exploded/hello`请求
2. 跳转到`Tomcat`服务器

3. `SpringMVC`的前端控制器收到所有请求

4. 根据请求地址和`@RequestMapping`进行匹配，确认使用哪个类的哪个方法
5. 前端控制器找到目标处理器和目标方法后，利用返回执行目标方法
6. 方法执行后的返回值，SpringMVC即认定这是要去的页面地址
7. 获得返回值后，使用视图解析器拼接得到完整的页面地址
8. 获得页面地址后，由前端控制器进行转发

## @RequestMapping

> 告诉`SpringMVC`这个方法用来处理哪个请求
>
> 注解中的/可以省略，默认从当前项目下开始
>
> 但是建议加上`/`

## 未指定配置文件位置

> 如果没有指定`SpringMVC`配置文件的地址
>
> 默认在：`WEB-INF/[...]-servlet.xml`下查找

## 问题

> `url-pattern=/`:不会拦截`jsp`为什么会拦截`html`

> 处理`*.jsp`是`tomcat`做的事；所有项目的`web.xml`都继承于`Tomcat服务器`的`web.xml`
>
> `DefaultServlet`是`Tomcat`中处理静态资源的？

+ 除了`.jsp`和`servlet`外剩下的都是静态资源
+ `index.html`是一个静态资源：`tomcat`就会在服务器下找到这个资源并返回
+ 前端控制器的`/`禁用了`tomcat`服务器的`DefaultServlet`

> 即：`Tomcat服务器`中有一个`DefaultServlet`定义了`url-pattern=/`
>
> 我们项目中配置前端控制器：`url-pattern=/`
>
> 静态资源会来到`DispatcherServlet（前端控制器）`查找哪个方法的`RequestMapping`为`index.html`
>
> 因为不存在，故无法访问

> 为何`.jsp`能正常访问

+ 因为我们没有拦截服务器(`Tomcat中的web.xml`)中的`JspServlet`配置