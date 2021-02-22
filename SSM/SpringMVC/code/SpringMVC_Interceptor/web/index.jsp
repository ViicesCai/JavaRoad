<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
  <head>
    <title>Home</title>
  </head>
  <body>
    <p>拦截器测试</p>
    <a href="${ctp}/test01">test01</a>
  </body>
</html>
