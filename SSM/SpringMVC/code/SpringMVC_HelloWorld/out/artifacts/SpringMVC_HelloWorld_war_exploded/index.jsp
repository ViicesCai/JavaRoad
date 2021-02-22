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
    <br>

    <a href="controller/request">Request</a>
    <br>

    <form action="controller/request">
      <p>通过POST方式发送请求</p>
      <input type="submit">
    </form>
    <br>

    <p>规定请求参数</p>
    <a href="controller/request03">Request03</a>
    <br>

    <p>指定请求头</p>
    <a href="controller/request04">Request04</a>
    <br>

    <hr/>

    <p>RequestMapping-Ant风格的URL</p>

    <p>匹配一个字符</p>
    <a href="ant/request01x">AntRequest01</a>
    <br>

    <p>PathVariable</p>
    <a href="ant/pathVariableRequest/cai">PathVariableRequest</a>
    <br>
  </body>
</html>
