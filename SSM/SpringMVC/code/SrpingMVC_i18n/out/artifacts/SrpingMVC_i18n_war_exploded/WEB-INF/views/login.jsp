<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/22
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录信息</title>
</head>
<body>
    <!-- 国际化区域信息是决定国际化显示的决定性因素 -->
    <h1><fmt:message key="welcomeinfo"/></h1>
    <form>
        <fmt:message key="username"/><input> <br>
        <fmt:message key="password"/><input> <br>
        <input type="submit" value="<fmt:message key="loginBtn"/>">
    </form>

    <!-- 点击超链接切换国际化 -->
    <a href="tologinpage?locale=zh_CN">中文</a>|<a href="tologinpage?locale=en_US">English</a>
</body>
</html>
