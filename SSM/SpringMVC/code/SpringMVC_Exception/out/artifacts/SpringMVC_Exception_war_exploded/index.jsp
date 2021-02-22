<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/22
  Time: 19:14
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
    <a href="${ctp}/handle01?i=0">test01</a> <br>
    <a href="${ctp}/handle02?username=CAI">test02</a> <br>
    <a href="${ctp}/handle03">test03</a> <br>
    <a href="${ctp}/handle04">test04</a> <br>
  </body>
</html>
