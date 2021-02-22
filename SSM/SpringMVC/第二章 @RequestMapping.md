# 第二章 @RequestMapping

## 概述

> 映射请求注解：`SpringMVC`使用`@RequestMapping`注解为控制器指定可以处理哪些`URL`请求
>
> 在控制器的类定义及方法定义处都可标注`@RequestMapping`

+ 标记在类上：提供初步的请求映射信息，相对于 `WEB `应用的根目录
+ 标记在方法上：提供进一步的细分映射信息，相对于标记在类上的 `URL`
  + 如果类与方法之上都有`@RequestMapping`：必须在该类下访问方法
+ 若类上未标注` @RequestMapping`，则方法处标记的` URL `相对于 `WEB` 应用的根目录

``` java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller")
public class MyController {

    @RequestMapping("/hello")
    public String firstRequest() {
        System.out.println("处理成功！!!");

        // 配置视图解析器后自动拼接：即 prefix + 返回值 + suffix
        return "success";
    }
}
```

> 访问`hello对应的方法路径应该为`：
>
> `http://localhost:8080/SpringMVC_HelloWorld_war_exploded/(项目的根路径)/controller/hello`

### 作用

> `DispatcherServlet`截获请求后，就通过控制器上`@RequestMapping`提供的映射信息确定请求所对应的处理方法

## 参数

> `@RequestMapping`除了可以使用请求`URL`映射请求外，还可以使用请求方法、请求参数及请求头映射请求

+ `value`：默认，即请求URL

  ``` java
  // @RequestMapping 第一个属性：value，默认，规定请求的URL
  @RequestMapping("/request")
  public String request() {
      System.out.println("RequestMappingTesterController - request - 请求成功！");
  
      return "success";
  }
  ```

+ `method`：请求方法，限定请求方式

  ``` java
  // @RequestMapping 第二个属性：method，规定请求方式
  // method = RequestMethod.POST：表示只接受该种类型的请求，默认全接收
  // 如果不是规点的方式的请求则报错
  @RequestMapping(value = "/request02", method = RequestMethod.POST)
  public String request02() {
      return "success";
  }
  ```

+ `params`：请求参数

  > 支持简单的表达式

  + `param1`: 表示请求必须包含名为`param1`的请求参数

    > `params = {"username"}`：发送请求时必须带上`uesrname`的参数
    >
    > 未带参数则404报错

  + `!param1`: 表示请求不能包含名为`param1`的请求参数

    > `params = {"!username"}`：发送请求时必须不能有`uesrname`的参数
    >
    > 带参数则404报错

  + `param1 != value1`: 表示请求包含名为`param1`的请求参数，但其值不能为`value1`

    > `params = {"username!=xxx"}`：发送请求时，`username`参数的值不能为`xxx`

  > 例：`{"param1=value1", "param2"}`: 请求必须包含名为`param1`和`param2`的两个请求参数，且`param1`参数的值必须为`value1`

+ `headers`：请求的报文头

  > 同`params`支持简单的表达式
  >
  > 通过`User-Agent`：让指定的浏览器能访问，其他浏览器无法访问
  >
  > `@RequestMapping(value = "/request04", headers = {"User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:85.0) Gecko/20100101 Firefox/85.0"})`：只能用火狐浏览器访问

+ `consumes`：只接受其内容是哪种内容

  > 即：规定请求头中的`Content-Type`

+ `produces`：告诉浏览器返回的内容类型是什么

  > 给响应头加上`Context-Type:text/html;charset=utf-8`

## 支持Ant路径风格

> `Ant`风格地址支持3种匹配符：`URL`中可以写模糊的通配符
>
> 模糊和精确多个匹配情况下，精确优先

+ `?`：能替代任意一个字符

  > `@RequestMapping("/request01?")`：仅匹配一个字符，0个多个都不行

+ `*`：能替代任意多个字符，或一层路径

+ `**`：能替代多层路径

## @PathVariable

> `@PathVariable`映射`URL`绑定的占位符

> 通过`@PathVariable`可以将`URL`中占位符参数绑定到控制器处理方法的入参中：
>
> `URL`中的`{xxx}`占位符可以通过`@PathVariable("xxx")`绑定到操作方法的入参中

``` java
 // 路径上可以有占位符，通过在任意处写上{变量名}：路径上的占位符只能是一层路径
 // 通过(@PathVariable("变量名") 可以获取该占位符的赋值
 @RequestMapping("/pathVariableRequest/{id}")
public String pathVariableRequest(@PathVariable("id") String id) {
    System.out.println("ant - PathVariableRequest - 占位符的值：" + id);
    
    return "success";
}
```

## RESTful

> 即：`Representational State Transfer`（表述性状态传递）
>
> 是一种软件架构风格

### 概述

> `REST`:（资源）表现层状态转化，是目前最流行的一种互联网软件架构

#### 资源(Resources)

> 网络上的一个实体，或者说是网络上的一个具体信息
>
> 即一段文本或图片等，可以使用`URI(统一资源定位符)`指向它，每种资源对应一个特定的`URI`
>
> `URI`即：每一个资源的独一无二的识别符

#### 表现层(Representation)

> 将资源具体呈现出来的形式称为其表现层
>
> 即：文本可用`.txt`表现，亦可使用`HTML`、`XML`、`JSON`等形式表线

#### 状态转化(State Transfer)

> 每发出一个请求，就代表了客户端和服务器的一次交互过程
>
> `HTTP协议`，是一个无状态协议，即所有的状态都保存在服务器端
>
> 因此，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”

> 即： HTTP 协议里面，四个表示操作方式的动词：`GET、POST、PUT、DELETE`分别对应四种基本操作

+ GET：获取资源
+ POST：新建资源
+ PUT：更新资源
+ DELETE：删除资源

### 配置

> 使用`REST`风格的`URL`地址

| 请求URL | 请求方式 |  表示含义   |
| :-----: | :------: | :---------: |
| /book/1 |   GET    | 查询1号图书 |
| /book/1 |  DELETE  | 删除1号图书 |
| /book/1 |   PUT    | 更新1号图书 |
| /book/1 |   POST   | 添加1号图书 |

> 若没有针对性的配置，`DELETE & PUT `默认请求方式都为`GET`

> 如何使Spring支持REST风格的请求?

1. `SpringMVC`中存在一个`Filter`，可以将普通的请求转换为规定形式的请求

   > `HiddenHttpMethodFilter`：浏览器`form`表单只支持`GET`与`POST`请求，而`DELETE、PUT`等`method`并不支持，`Spring3.0`添加了一个过滤器，可以将这些请求转换为标准的`http`方法，使得支持`GET、POST、PUT、DELETE`请求

   配置`HiddenHttpMethodFilter`：在 `web.xml `中配置

   ``` xml
   <!-- 配置 HiddenHttpMethodFilter：使Spring支持Rest风格的请求 -->
   <filter>
       <filter-name>HiddenHttpMethodFilter</filter-name>
       <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
   </filter>
   
   <filter-mapping>
       <filter-name>HiddenHttpMethodFilter</filter-name>
       <!-- 拦截所有请求 -->
       <url-pattern>/*</url-pattern>
   </filter-mapping>
   ```

2. 如何支持其他形式请求？

   + 创建`POST`类型的表单，表单项中携带一个 `_method `的参数，这个` _method `的值为：`DELETE 或 PUT`

     > 注意：如果是`Tomcat8.0`及以上，可能会出现`405`错误
     >
     > 解决方法：在返回的`jsp`页面头部添加：`isErrorPage="true"`

     ``` jsp
     <%@ page contentType="text/html;charset=UTF-8" language="java"
             isErrorPage="true" %>
     ```

     > `index.jsp`

     ``` jsp
     <%--
       Created by IntelliJ IDEA.
       User: CAI
       Date: 2021/2/7
       Time: 12:24
       To change this template use File | Settings | File Templates.
     --%>
     <%@ page contentType="text/html;charset=UTF-8" language="java" %>
     <html>
       <head>
         <title>首页</title>
       </head>
       <body>
       <!-- 发起图书的增删改查功能：使用REST风格的URL地址 -->
       <!--
         请求URL  请求方式  表示含义
         /book/1  GET      查询1号图书
         /book/1  DELETE   删除1号图书
         /book/1  PUT      更新1号图书
         /book/1  POST     添加1号图书
     
         DELETE & PUT 默认请求方式都为 GET
         如何使Spring支持REST风格的请求
         1. SpringMVC中存在一个Filter，可以将普通的请求转换为规定形式的请求：
               配置HiddenHttpMethodFilter：在 web.xml 中配置
     
         2. 如何支持其他形式请求
               创建POST类型的表单
               表单项中携带一个 _method 的参数
               这个 _method 的值为：DELETE 或 PUT
               注意：如果是Tomcat8.0及以上，可能会出现 405错误
                   解决方法：在返回的 jsp 页面头部添加：isErrorPage="true"
       -->
       <a href="book/1">查询图书</a>
     
       <form action="book/1" method="post">
         <input type="submit" value="添加图书">
       </form>
     
       <!-- 发送DELETE请求 -->
       <form action="book/1" method="post">
         <!-- 大小写不区分 -->
         <input type="hidden" name="_method" value="DELETE">
         <input type="submit" value="删除图书">
       </form>
     
       <!-- 发送PUT请求 -->
       <form action="book/1" method="post">
         <input type="hidden" name="_method" value="PUT">
         <input type="submit" value="更新图书">
       </form>
       </body>
     </html>
     ```

     > `BookController.java`

     ``` java
     import org.springframework.stereotype.Controller;
     import org.springframework.web.bind.annotation.PathVariable;
     import org.springframework.web.bind.annotation.RequestMapping;
     import org.springframework.web.bind.annotation.RequestMethod;
     
     /**
      * @author CAI
      * @time 2021/2/7
      */
     @Controller
     public class BookController {
     
         @RequestMapping(value = "/book/{book_id}", method = RequestMethod.GET)
         public String getBook(@PathVariable("book_id") Integer id) {
             System.out.println("查询到：" + id + "号图书");
     
             return "success";
         }
     
         @RequestMapping(value = "/book/{book_id}", method = RequestMethod.POST)
         public String addBook(@PathVariable("book_id") Integer id) {
             System.out.println("新增：" + id + "号图书");
     
             return "success";
         }
     
         @RequestMapping(value = "/book/{book_id}", method = RequestMethod.DELETE)
         public String deleteBook(@PathVariable("book_id") Integer id) {
             System.out.println("删除：" + id + "号图书");
     
             return "success";
         }
     
         @RequestMapping(value = "/book/{book_id}", method = RequestMethod.PUT)
         public String updateBook(@PathVariable("book_id") Integer id) {
             System.out.println("更新：" + id + "号图书");
     
             return "success";
         }
     }
     ```

     > `success.jsp`

     ``` jsp
     <%--
       Created by IntelliJ IDEA.
       User: CAI
       Date: 2021/2/7
       Time: 12:46
       To change this template use File | Settings | File Templates.
     --%>
     <%@ page contentType="text/html;charset=UTF-8" language="java"
             isErrorPage="true" %>
     <html>
     <head>
         <title>成功</title>
     </head>
     <body>
         <h1>操作成功</h1>
     </body>
     </html>
     ```

     ### 为什么参数名必须为_method

> 通过`_method`来获取请求方式

``` java
/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

/**
 * {@link javax.servlet.Filter} that converts posted method parameters into HTTP methods,
 * retrievable via {@link HttpServletRequest#getMethod()}. Since browsers currently only
 * support GET and POST, a common technique - used by the Prototype library, for instance -
 * is to use a normal POST with an additional hidden form field ({@code _method})
 * to pass the "real" HTTP method along. This filter reads that parameter and changes
 * the {@link HttpServletRequestWrapper#getMethod()} return value accordingly.
 * Only {@code "PUT"}, {@code "DELETE"} and {@code "PATCH"} HTTP methods are allowed.
 *
 * <p>The name of the request parameter defaults to {@code _method}, but can be
 * adapted via the {@link #setMethodParam(String) methodParam} property.
 *
 * <p><b>NOTE: This filter needs to run after multipart processing in case of a multipart
 * POST request, due to its inherent need for checking a POST body parameter.</b>
 * So typically, put a Spring {@link org.springframework.web.multipart.support.MultipartFilter}
 * <i>before</i> this HiddenHttpMethodFilter in your {@code web.xml} filter chain.
 *
 * @author Arjen Poutsma
 * @author Juergen Hoeller
 * @since 3.0
 */
public class HiddenHttpMethodFilter extends OncePerRequestFilter {

	private static final List<String> ALLOWED_METHODS =
			Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(),
					HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));

	/** Default method parameter: {@code _method}. */
	public static final String DEFAULT_METHOD_PARAM = "_method";

	private String methodParam = DEFAULT_METHOD_PARAM;


	/**
	 * Set the parameter name to look for HTTP methods.
	 * @see #DEFAULT_METHOD_PARAM
	 */
	public void setMethodParam(String methodParam) {
		Assert.hasText(methodParam, "'methodParam' must not be empty");
		this.methodParam = methodParam;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest requestToUse = request;

		if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
			String paramValue = request.getParameter(this.methodParam);
			if (StringUtils.hasLength(paramValue)) {
				String method = paramValue.toUpperCase(Locale.ENGLISH);
				if (ALLOWED_METHODS.contains(method)) {
					requestToUse = new HttpMethodRequestWrapper(request, method);
				}
			}
		}

		filterChain.doFilter(requestToUse, response);
	}


	/**
	 * Simple {@link HttpServletRequest} wrapper that returns the supplied method for
	 * {@link HttpServletRequest#getMethod()}.
	 */
	private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

		private final String method;

		public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
			super(request);
			this.method = method;
		}

		@Override
		public String getMethod() {
			return this.method;
		}
	}
}
```

