# Tomcat

默认端口为：8080

进行JavaWeb开发（JSP/Servlet)都需要用到

使用前请注意所使用的JDK版本是否与 tomcat 对应，且环境配置必须正确

## 目录结构

### bin

+ 主要用来存放 tomcat 命令，主要分为两大类：.sh（Linux命令）、.bat（Windows命令）
+ `startup`启动 tomcat
+ `shutdown`关闭 tomcat
+ `catalina`设置 tomcat 内存

### conf

+ 存放 tomcat 的配置文件
+ `server.xml`设置端口号、设置域名或IP、默认加载的项目、请求编码
+ `web.xml`设置 tomcat 支持的文件类型
+ `context.xml`配置数据源
+ `tomcat-users.xml`配置管理 tomcat 的用户与权限
+ `Catalina`设置默认加载的项目

### lib

+ tomcat 运行时需要加载的包：如：连接数据库的 JDBC 包

### log

+ tomcat 运行中生产的日志文件

### temp

+ tomcat 运行中产生的临时文件

### webapps

+ 存放应用程序，当 tomcat 启动时就会去加载这些程序，可以以文件夹、war 包、jar 包的形式发布
+ 将开发的项目放入该目录，也可以做映射，存放在别的地方，映射过来
+ 默认访问 ROOT 文件夹

### work

+ 存放 tomcat 运行时的编译文件：.class

  jsp -> java -> class

## 配置 tomcat 

+ 修改端口号：`server.xml`：`Connector`

+ 修改项目映射地址

  在 `server.xml` 下 添加：

  `<Context path="指定项目名（/testProject）" docBase="项目部署位置（/test/webapps/testProject）"> </Context>`

+ 自定义 JSP 项目文件需要包含一下文件夹

  + WEB-INF：建议直接从 root 直接复制过来：如果内容不正确会导致 tomcat 无法访问自己的 jsp 项目

    web.xml：Web 应用程序配置文件

  + lib：第三方依赖库

  + classess：字节码文件夹

  + index.jsp

    在 html 中通过 <%  java code %> 的方式 嵌套 java 代码

    类似于 php

+ 常见的几种状态码

  + 200：正常
  + 300/301：页面重定向
  + 404：资源不存在
  + 403：权限不足（该目录设置成不可见，或 web.xml 配置错误）
  + 500：服务器内部错误（代码错误）

## 项目的虚拟路径

将 web 项目配置在 tomcat/webapps 以外的地方

打开 tomcat/conf/server.xml

``` xml
<!-- <host>中添加 -->
<Context docbase="项目的实际路径" path="虚拟路径(相对于 tomcat/webapps的路径，也可以是绝对路径)"/>
<!-- 这里我的WEB项目在D盘 而 tomcat 在C盘 -->
<Context docbase="D:\MyWebPrj" path="/MyWebPrj"/>
<!-- 上面则代表，访问的是c:\tomcat\webapps\MyWebPrj -->
```

也可在在

tomcat/conf/Catalina/localhost 中新建：项目名.xml，添加此语句

```xml
<Context docbase="D:\MyWebPrj" path="/MyWebPrj"/>
```

或者将 自己的项目改成 ROOT：这样就可以省略项目名，直接访问：一般不这样使用，可以通过虚拟主机来做映射会方便一些

# JSP

## 执行过程

+ JSP 与 Servlet

  + JSP：Java 服务器页面，本身也是Servlet，替代Servlet 输出 HTML，支持HTML 和 Java 代码的嵌套
  + Servlet：遵循 Servlet 开发的Java类，由服务器调用，运行在服务器端
  + 将 JSP 看成前端 Servlet 看成后端，实际上与 HTML + PHP 差不多

+ 使用浏览器访问 jsp 时，Tomcat 会将该 jsp 转换成 文件名_jsp.java，并编译该文件，编译后的文件会保存在 classess 文件夹中

+ 之后调用时，Tomcat 就不再重新编译，直接调用 .class 文件发送给浏览器

  对其运行有兴趣的话，可以查看 jsp 的 java 类源码，这里的服务器上 可以看成 虚拟机JVM，只是执行了该 Java 代码而已

## 使用 Eclipse 开发JSP

### 关联 Tomcat

Window -> Preferences -> Server -> Runtime Environments

![image-20200501194638160](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501194638160.png)

通过 Add 添加自己的 Tomcat 版本

![image-20200501195011189](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501195011189.png)

Next，将自己的 Tomcat 添加进来，Browse 选择 Tomcat 的路径，然后 Finish

+ JRE：选择自己的 JDK 版本（JDK包含JRE）

+ 如果这里没有JDK 点击左侧 Installed JREs

![image-20200501195059412](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501195059412.png)

+ 将自己的JDK版本添加进来

###  添加服务器

点击此处

![image-20200501195353643](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501195353643.png)

+ 选择自己刚刚添加的 Tomcat 版本：选择的版本必须和之前关联的一致

  ![image-20200501195453176](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501195453176.png)

+ 点击Next，Finsh(这里没有可用的项目是因为咱们没有创建JSP项目)

![image-20200501195658107](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501195658107.png)

+ 成功后自动生成一个 Servers 文件夹用于配置（Tomcat：只是将Tomcat的一些配置内容复制过来而已，在这里配置文件并不会影响到对应目录的配置，仅仅在当前项目下有效）

  + 如果想要两个地方的配置相互关联起来

    单击此处，会出现如下画面，选择箭头处即可

    这边我呈现灰色的原因是我启动过Tomcat服务了，如果是第一次启动，是可以直接配置的

    如果你和我一样：1.先关闭服务 2.右键项目（只有配置了项目才能启动服务器）Remove 3.双击该Server

    ![image-20200501200141107](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501200141107.png)

### 创建 JSP 项目

![image-20200501200747170](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501200747170.png)

![image-20200501200907748](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501200907748.png)

![image-20200501200925116](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501200925116.png)

![image-20200501200948601](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501200948601.png)

![image-20200501201124518](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501201124518.png)

![image-20200501201150128](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501201150128.png)

此时已经可以开启服务并通过端口访问

项目结构如下：通过修改`welcome-file`修改默认访问的文件

![image-20200501201231089](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200501201231089.png)

#### 统一字符集编码

这里不再赘述 如何修改编码

+ 将整个工作区的编码设置成 UTF-8 （只有之后生成的文件有效）
+ 将整个项目的编码设置成 UTF-8
+ 将 jsp 文件编码设置成 UTF-8

## JSP 页面元素

### 语法

``` 
<% java code %> 局部变量、Java 语句
<%! java code %> 全局变量、方法
<%=输出表达式 %>

例	
<%!
	public String info;
	public void init() {
		info = "Hello ! this is my jsp";
	}
%>
	
<%
	String name = "JavaCode";
	out.print(name); 可以直接解析 HTML 代码
	注意：out.println()并不会自动换行，需要使用 <br>
	init();
%>
	
<%="info：" + info %>

输出：JavaCode info：Hello ! this is my jsp 

修改 web.xml、配置文件、java 需要重启 tomcat 服务
修改 Jsp、html、css、js 则不需要
```

### 指令

```
<%@ page ... %>
page 的属性：
language：jsp 使用的脚本语言
import：导入类
pageEncoding：jsp 文件自身的编码
contentType：浏览器解析 jsp 的编码

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
```

### 注释

```
根据使用的代码写注释
java：// /* code */
html：<!-- -->
jsp：<%-- --%>
```

### JSP 内置对象

不需要 new 即可创建对象：九大内置对象

#### out

+ 输出对象：向客户端输出内容

#### request

+ 请求对象：存储 客户端 向 服务端 发送的请求信息

``` java
// 常用方法
String getParameter(String name); // 根据请求的字段名：key 返回字段值：value
String[] getParameterValues(String name); // 根据请求的字段名：key 返回多个字段值：values（多选按钮通常会返回多个值）
void setCharacterEncoding("编码格式"); // 设置请求编码 默认：utf-8
getRequestDispatcher("文件名.jsp").forward(request, response); // 请求转发的方式调转页面 当前页面到 设置的页面
ServerContext getServerContext(); // 获取项目的 ServerContext 对象

// 请求方式
// 1. get：默认 在 URL 显式的表示所有请求信息（至多 4 - 5 KB）容量有限
// 2. post：在 URL 中不会显示，文件上传必须是 post
// 通常情况下，使用 post，更安全

// 例子：注册
```

#### response

客户端 -> 服务端：request

服务端 -> 客户端：response

+ 响应对象：服务器收到客户端请求后做出的响应

``` java
// 常用方法
void addCookie(Cookie cookie); // 服务器向客户端增加 cookie 对象
void sendRedirect(String location) throws IOException; // 页面跳转的一种方式（重新定向）
void setContentType(String type); // 设置服务端响应的编码（设置服务端的 Content类型）

// 注意点
// sendRedirect 与 getRequestDispatcher("文件名.jsp").forward(request, response) 都能做页面跳转但是本质上存在如下的区别
// 前者：地址栏会改变成指定的jsp，后者：地址栏不会改变
// 前者：第一次请求的数据不保留，后者：第一次请求的数据会保留
// 前者：转发次数 2，后者：转发次数 1

// sendRedirect：大致的原理是第一次客户端请求的数据到服务器中，第一次服务端响应通知客户端地址 重定向
// 于是 客户端又发送了第二次请求，此时服务器做出第二次响应，但是请求的数据已经丢失了

// getRequestDispatcher("文件名.jsp").forward(request, response) 仅仅是将 客户端发送给服务器的请求做出转发，交给下一个地址，这一切都在服务端中完成，所以请求的数据不会丢失

// 作用域：
// request：仅在同一次请求时有效，作用域：Request 即它的有效范围是当前请求的周期
// 所谓请求周期，就是指从http请求发起，到服务器处理结束，返回响应的整个过程。
// 这也就是重定向与跳转的区别，跳转仅仅是跳转到其他的JSP页面，客户端并没有重新发起请求，所以请求的用户数据跳转到的页面能继续使用

// response：仅在当前的jsp页面中有效， 作用域：page
// 即跳转到其他页面之前，都可以使用

// 例子：登录
```

#### session

+ cookie(存在于客户端，不是内置对象)：cookie 是由 服务端产生，再发送给客户端保存

  + 本地缓存：提高访问服务端的效率，但是安全性差

  + cookie 数据结构：key = value，键值对

    `java.servlet.http.Cookie` 

    ``` java
    // 构造方法
    public Cookie(String key, String value);
    
    // 常用方法
    String getName(); // 获取键
    String getValue(); // 获取值
    void setMaxAge(int expiry); // 保存的最大有效时间(秒)
    
    // 服务端 发送 cookie 给客户端的过程
    // 服务端准备 cookie
    response.addCookie(Cookie cookie);
    // 页面跳转（转发，重定向）
    // 客户端获取 cookie
    request.getCookies(); // 获取所有的 cookie
    
    // 注意
    // 不能直接获取某一个单独的 cookie，只能一次性获取所有的 cookie
    // 除了自己设置的 cookie 对象，还会获取到一个 name = JSEESIONID 的cookie
    // 建议 cookie 只放入英文和数字，不保存中文：需要进行编码解码处理
    
    // 例子：实现记录用户名功能
    ```

+ session（存储在服务端）：会话，浏览网站从开始浏览到关闭网页等于一次会话

  + session 机制
    + 第一次请求服务端时：服务端会在内部产生 一个 Session对象（用于保存此客户端的信息），并且每个对象都有一个唯一的SessionID(用于区分其他session)，服务端生成一个cookie（name=JSESSIONID,value=SessionID），并在响应客户端的请求时，将此 cookie 发送给客户端
    + 因此，客户端cookie中的JSEESION与服务端的SessionID一一对应
    + 第n次请求服务端时：服务端会先用客户端cookie中的JSESSIONID去服务端的session中匹配sessionid，如果匹配成功（JESSIONID = SessionID）说明此用户不是第一次访问，无需登录；如果服务端发现此请求不存在 sessionId，则会生成一个name=JSESSION的cookie ，并返回给客户端
  + session存储在服务端
  + session是在同一个用户（客户端）请求时共享

``` java
// 常用方法
String getId(); // 获取 sessionId
boolean isNew(); // 是否是第一次访问
void invalidate(); // 使 session 失效（退出登录或注销）
void setAttribute(String name, String value); // 设置 session 的值;如果该属性已存在，则可以修改
Object getAttribute(String name); // 根据 name 取得 session 的 value
void removeAttribute(String name); // 根据属性名，删除对象
void setMaxInactiveInterval(); // 设置最大有效非活动时间（距离下一次访问的时间间隔）秒
int getMaxInactiveInterval(); // 获取最大有效非活动时间

// 作用域：在同一个会话中共享
// 所谓当前会话，就是指从用户打开浏览器开始，到用户关闭浏览器这中间的过程。这个过程可能包含多个请求响应。也就是说，只要用户不关浏览器，服务器就有办法知道这些请求是一个人发起的，整个过程被称为一个会话（session），而放到会话中的变量，就可以在当前会话的所有请求里使用
```

+ 两者的区别：
  + 存储位置
    + session：服务端
    + cookie：客户端
  + 安全性
    + session：相对安全
    + cookie：比较不安全
  + 保存的内容
    + session：object
    + cookie：string

#### application

+ 全局对象：实现了用户间数据的共享，可存放全局变量。它开始于服务器的启动，直到服务器的关闭，在此期间，此对象将一直存在

``` java
// 常用方法
String getContextPath(); // 虚拟路径
String getRealPath(String name); // 绝对路径（相对于虚拟路径的绝对路径）
```



```
out：
：
常见方法：
pageContext
response
session
aalication
config
page
exception
```

#### config

+ 配置对象：存放服务器的配置信息

#### page

+ 当前JSP页面对象：相当于 Java 中的 this

#### exception

+ 异常对象

### 作用域

四个作用域范围（以下顺序代表范围从小到大）

``` java
// 公有方法
Object getAttribute(String name); // 根据属性名 获得属性值
void setAttribute(String name, Object obj); // 设置属性值（新增/修改）
void removeAttribute(String name); // 根据属性名，删除对象
```

+ pageContext：JSP页面容器，仅在当前页面有效
  + 跳转后即失效
+ request：请求对象，同一次请求有效
  + 重定向，即重新发送请求，即失效
  + 跳转，并没有重新发送一个请求，所以还是有效的
+ session：会话对象，同一次会话有效
  + 即关闭/切换浏览器后无效
+ application：全局对象，全局有效（整个项目）
  + 即关闭服务器后无效

+ 以上范围对象，尽量使用最小范围的对象，范围越大，造成的性能损耗越多

## JDBC

可以为多种关系型数据库 DBMS 提供统一的访问方式，用 Java 操作数据库

![image-20200505114706139](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200505114706139.png)

### JDBC API

+ 提供各种操作访问接口，如：Connection、Statement、PreparedStatement、ResultSet

+ 主要功能：
  + 1.与数据库建立连接
  + 2.发送 SQL 语句
  + 3.返回处理结果

+ 具体实现：
  + DriverManager：管理 JDBC 驱动
  + Connection：连接：通过 DriverManager.getConnection 获取
  + Statement（PreparedStatement）：进行 增删改查 操作：通过 Connection 得到
  + CallableStatement：调用数据库中的 存储过程、存储函数：通过 Connection 得到
  + ResultSet：结果集（如：查询结果）：通过 Statement 得到
  
+ 主要步骤：

  ``` java
  // 1. 导入驱动，加载具体的驱动类
  Class.forName("com.mysql.cj.jdbc.Driver"); // 修改参数，即可加载不同的驱动类
  
  // 2. 与数据库建立连接
  DriverManager.getConnection(URL, USER, PWD); // URL 即代表数据库的链接地址；返回一个 Connection
  
  // 3. 执行
  // Statement 操作数据库：有 sql 注入的风险
  // 增删改：executeUpdate(String sql);
  // 查询：executeQuery(String sql);
  
  // PreparedStatement 继承 Statement
  // 与 Statement 的区别：
  // 通过 ? 占位符，将 sql语句 参数化，有效防止了 sql 注入
  // 预编译 sql 语句：在创建 PreparedStatement 对象时需要添加 sql 语句，区别于 statement
  
  // 操作同上，不需要执行 sql 语句了
  // 在执行之前，可以同个 setXxx()、getXxx() 预先处理数据
  // 例：sql = "insert into Student values(?, ?, ?)"
  // 通过 setXxx(参数，下标); 下标 = 1，代表从第一个问号开始，设置其具体的参数
  // execute(); 也可以直接执行
  
  // 注意：下标是从 1 开始而不是 0
  
  // 4. 结果集
  // ResultSet：保存查询结果返回的结果集
  // next()：光标下移，判断下一行是否有数据：true/false
  // previous()：光标上移 同上
  // getXxx(字段名， 下标); 获取对应的数据类型的数据
  ```

+ 推荐使用：PreparedStatement

  + 编码更加简便

    + 通过 ? 占位符，可以省去许多麻烦
    + 预编译 SQL，在执行之前可以通过 setXxx 设置具体的参数
    + 避免了拼接 sql 语句

  + 提高性能

    + 预编译 sql ，执行的时候 不用在编译，对于批量处理操作，提高了性能

  + 安全

    + Statement 存在被 sql 注入的风险

      ``` sql
      输入：任意值 ' or 1=1 -- 
      ```

      即：用户输入 和 我们编写的 sql 语句混为一体

    + 占位符 赋值避免了此种情况

### JDBC DirverManager

+ 管理不同的数据库驱动

### 数据库驱动

+ 由相应的数据库厂商提供，用于连接、操作数据库

### 切换数据库

+ 只需要替换：驱动、具体驱动类、连接字符串、用户名、密码

### 存储过程

+ 存储过程：是为了完成特定功能的SQL语句集，经编译创建并保存在数据库中，用户可通过指定存储过程的名字并给定参数(需要时)来调用执行
  
  + 存储过程思想上很简单，就是数据库 SQL 语言层面的代码封装与重用
+ 存储函数与存储过程一样，是由SQL语句和过程式语句组成的代码片段

+ 区别
  + 存储函数：不能拥有输出参数，可以直接调用 不需要 cell 语句，必须有 return
  + 存储过程：可以拥有输出参数，需要使用 cell 调用，没有 return

+ Connection.prepareCall(参数：存储过程/存储函数名)

  + 存储过程：无返回值，使用 out 参数代替
  + 存储函数：有返回值

  ``` java
  // 此代码仅作参考，实际并不能运行
  
  // 1. 调用存储过程对象
  CallableStatement cstmt = Connection.prepareCall("存储过程语句：{call }");
  // 2. 通过 setXxx 处理输出参数值
  // 3. 通过 registerOutParameter 处理输出参数类型
  // 4. execute() 执行
  // 5. getXxx() 接收结果
  
  // 1. 调用存储函数
  cstmt = conn.prepareCall("{? = call countNumber(?, ?)");
  // 2. 于存储过程仅仅是参数位置的区别
  
  ```

### 处理大型数据

以 mysql 为例

+ Text：大型文本数据（64KB）
  + MediumText（16MB）
  + LongText（4GB）
+ Blob：大型文件数据（64KB）
  + MediumBlob（16MB）
  + LongBlob（4GB）

### JSP 访问数据库

+ 在 JSP 中嵌套 JDBC 的相关操作
+ 相关的逻辑代码 写在 src 文件夹中
  + 即形成了一个 JavaBean
    + 封装数据：对应一张数据表
    + 封装业务逻辑：用于执行某个操作
  + 提高代码复用性
  + 减轻 jsp 的复杂程度

### 小结

+ 之前写过具体的JDBC 操作，这里仅作补充
+ 学习JDBC 仅仅掌握具体的操作模板即可完成大部分的操作

# MVC

M：Model，模型

+ 某个功能：使用 JAVA实现
  + 例：登录功能（LoginDao）
  + 增加学生功能
  + 删除学生功能
+ 处理业务逻辑
+ 处理数据

V：View，视图

+ 负责页面的显示
  + 表单、表格
  + html\css\jsp\js
+ 用户的交互

C：Controller，控制器

+ 接受请求，跳转到模型或视图中进行处理；处理完毕后，将结果返回给请求页处
  + 可以使用 JSP 实现
  + 一般建议使用 Servlet 实现控制器

## 处理流程

![image-20200530084944766](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200530084944766.png)

# Servlet

## 什么是Servlet

+ 是用Java编写的服务器端程序,其主要功能在于交互式地浏览和修改数据，生成动态Web内容

## 实现

+ Java 类必须符合一定的规范

  + 必须继承`javax.servlet.http.HttpServlet`
  + 重写其中的`doGet()`或`doPost()`

  ``` java
  public class WelcomeServlet extends HttpServlet {
  	@Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		System.out.println("doGet...");
  	}
  	
  	@Override
  	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		System.out.println("doPost...");
  	}
  }
  ```

  

+ `doGet()`接受并处理所有 get 提交方式的请求

+ `doPost()`接受并处理所有 post 提交方式的请求

+ 要使用 Servlet 必须配置

  + Servlet 2.5：web.xml

    ``` xml
    <servlet>
        <!-- 3 -->
      	<servlet-name>welcome</servlet-name>
        <!-- 对应的 class 文件：包名 + 类名：4 -->
      	<servlet-class>servlet.WelcomeServlet</servlet-class>
    </servlet>
      
    <servlet-mapping>
        <!-- 与 servlet中的 name 对应：2 -->
      	<servlet-name>welcome</servlet-name>
        <!-- 浏览器的访问地址：1 -->
      	<url-pattern>/WelcomeServlet</url-pattern>
    </servlet-mapping>
    
    // 访问顺序：1->2->3->4：url 中的名称可以自行命名，注意跳转路径的对应关系，确保在URL路径中的名称与之相应
    ```

  + Servlet 3.0：@WebServlet

    ``` java
    @WebServlet("/WelcomeServlet") // 访问地址
    public class WelcomeServlet extends HttpServlet {
    	@Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		System.out.println("doGet...");
    	}
    	
    	@Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		System.out.println("doPost...");
    	}
    }
    ```

    

### 使用 Eclipse 自动生成

![image-20200530094130785](C:\Users\Vivi_\AppData\Roaming\Typora\typora-user-images\image-20200530094130785.png)

![image-20200530094235430](C:\Users\Vivi_\AppData\Roaming\Typora\typora-user-images\image-20200530094235430.png)

![image-20200530094338792](C:\Users\Vivi_\AppData\Roaming\Typora\typora-user-images\image-20200530094338792.png)

## 项目根目录

`WebContent` `src(所有的构建路径)`

+ 例：如果 `WebContent` 下有一个 `index.jsp`

  ``` html
  <a href="Welcome"></a>
  ```

  + 则该路径的寻找范围为：`WebContent` 与 `src`
  + 与 `web.xml` 中的匹配关系是由此建立的

+ `/`的含义

  + jsp 文件中：服务器的根路径
  + web.xml 中：项目根路径

## 生命周期

1. 加载

2. 初始化：`init()`在 `Servlet`被加载并实例化之后执行：仅在第一次访问时执行，也可以修改为 Tomcat 服务启动时执行

   + Servlet 2.5

     ``` xml
     <!-- web.xml -->
     <servlet>
         <load-on-startup>
             1
         </load-on-startup>
     </servlet>
     ```

   + Servlet 3.0

     ``` java
     @WebServlet(value = "/WelcomeServlet", loadOnStartup = 1) // 访问地址
     ```

     

3. 服务：`service()`

   + `doGet`
   + `doPost`

4. 销毁：`destroy()` `Servlet`被系统回收时执行：仅在关闭 Tomcat 服务时执行

5. 卸载

+ 加载和卸载由 Servlet 容器自动处理

## Servlet API

+ 由两个软件包组成：HTTP协议的软件包、除了HTTP协议以外的其他软件包

+ Servlet API 可以适用于任何通信协议
+ Servlet 位于 `javax.servlet.http 包`中的类和接口，是基础 HTTP 协议

### 继承关系

![image-20200530112933586](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200530112933586.png)

+ `ServletConfig：接口`

  + `getServletContext()：获取 Servlet 上下文对象：application`

    常见方法

    + `getContextPath()`：相对路径

    + `getRealPath()`：绝对路径

    + `setAttribute()`

    + `getAttribute()`

    + `getInitParameter(String name)：在当前 web 容器范围内，获取名为 name 的参数值(初始化参数)：可以认为是一个全局的属性`

      `Servlet 2.5`

      ``` xml
      web.xml
      <!-- 在整个WEB容器中 设置初始化参数 -->
      <context-param>
          <param-name>globalParam</param-name>
          <param-value>globalValue</param-value>
      </context-param>
      ```

      ``` java
      public class WelcomeServlet extends HttpServlet {
      	@Override
      	public void init() throws ServletException() {
              // 获取当前 web 容器的初始化参数
              ServletContext servletContext = super.getServletContext();
              String value = servletContext.getInitParameter("globalParam");
              System.out.println("当前 Web容器 参数的值：" + value);
          }
      }
      ```

      `Servlet 3.0`

      不能使用注解的方式设置 web的参数，配置同 2.5 一致

  + `getInitParameter(String name)：在当前 Servlet 范围内，获取名为 name 的参数值(初始化参数)`

    `Servlet 2.5`

    ``` xml
    web.xml
    <servlet>
        <init-param>
            <param-name>servletParamName</param-name>
            <param-value>servletParamValue</param-value>
        </init-param>
    </servlet>
    ```

    `Servlet 3.0`

    ``` java
    @WebServlet(value = "/WebcomeServlet", loadonStartup = 1, initParams = {@WebInitParam(name = "servletParamName", value = "servletParamValue")})
    public class WelcomeServlet extends HttpServlet {
    	@Override
    	public void init() throws ServletException() {
            // 获取当前 Servlet 的初始化参数
            String value = super.getInitParameter("servletParameterName"); // 获取 web.xml 中的初始化参数
            System.out.println("当前 Servlet 参数的值：" + value);
        }
    }
    ```

### Service

![](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200530122018387.png)

+ 所以仅仅继承 HttpServlet 就可以重写某一个方法，而不用全部重写
+ Eclipse 可以直接创建 servlet

## 在 Java 中获取 JSP 内置对象

```java
out、session、application
out: PrintWriter out = response.getWriter();
session: request.getSession();
application: request.getServletContext();
```

# 三层结构

与 MVC设计模式的目标一致：为了解耦合、提高代码复印

区别：二者对项目理解的角度不同

## 组成

+ 表示层（USL，User Show Layer：视图层）
  + 前台：对应MVC中的View，用于和用户交互、页面的显示
    + 如：Jsp、JS、html、Jquery等前端技术
    + 存放位置：`WebContent`
  + 后台：对应于MVC中的Controller，用于控制跳转、调用业务逻辑层
    + 如Servlet（SpringMVC、Struts2），位于`xxx.servlet`包中
+ 业务逻辑层（BLL，Business Logic Layer：Service层）
  + 接收表示层的请求、调用
  + 组装数据访问层、逻辑性的操作
    + 一般位于`xxx.service`包(也可以命名为`xxx.manager` `xxx.bll`)

+ 数据访问层（DAL，Data Access Layer：Dao层）
  + 直接访问数据库的操作（原子性操作：CRUD）
    + 一般位于`xxx.dao`包

## 三层间的关系

![image-20200530221608117](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200530221608117.png)

+ 上层将请求传递给下层，下层处理后返回给上层
+ 上层依赖于下层：持有成员变量即必须先有数据库才可能存在DAO，DAO依赖于数据库

## 建议

+ 一个 Servlet 对应一个功能：即CRUD操作需要四个Servlet

## 优化

### 加入接口

+ 面向接口开发：先创建接口---再实现类

  + 接口：interface
    + 命名：`I实体类层所在包名` I实体类Service` `IStudentService`
  + 实现类：implements
    + 命名：`实体类层所在包名Impl` `实体类ServiceImpl` `StudentServiceImpl`

+ 使用实现类时

  `接口 xxx = new 实现类();` : 多态

### 数据库工具类

+ 简化数据库操作：简化 Dao 层的代码量
+ 封装通用的 CRUD 操作

## 在 Tomcat 中 jsp 文件是如何运行

`index.jsp` -> `index_jsp.java` -> `index_jsp.class`

+ `jsp`翻译成`Java文件`以及编译后的`class文件`都存在于`tomcat`中的`work目录`

## 分页

+ 确定单页要检索的数据范围

  + 如：每页显示十条数据
  + 第一页：开始：1；结束：10
  + 第二页：开始：11；结束：20
  + `sqlserver/oracle`：默认从1开始
    + 第N页：开始：(n-1)*10+1；结束：n *10
  + `mysql`：默认从0开始
    + 第N页：开始：n*10；结束：(n+1) *10-1

+ `Mysql`

  ```sql
  select * from student limit n*10, 10;
  ```

### 实现

1. 数据总数：`select count(*)`
2. 页面大小：每页显示的数据条数
3. 总页数：`数据总数 % 页面大小 == 0 ? 数据总数/页面大小 : 数据总数/页面大小+1`
4. 当前页：页码
5. 当前页的对象集合（实体类集合）：每页所显示的所有数据

## 上传

`commons-io-2.4.jar`

`commons-fileupload-1.3.1.jar`

+ 将 jar 加入到项目的`lib`中

### 实现

+ 前台JSP：上传

  + 使用 form 表单

    ``` jsp
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
    	上传照片：<input type="file" name="spicture"><br>
        <input type="submit" value="提交">
    </form>
    ```

+ 后台Servlet

  + 上传到相应目录
  + 限制上传的文件类型、大小

