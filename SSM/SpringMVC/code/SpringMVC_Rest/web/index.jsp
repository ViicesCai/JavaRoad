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
