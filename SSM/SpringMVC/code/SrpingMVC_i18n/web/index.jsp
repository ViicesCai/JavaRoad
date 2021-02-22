<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/22
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <a href="${ctp}/tologinpage">登录页面</a>
  </body>
</html>
