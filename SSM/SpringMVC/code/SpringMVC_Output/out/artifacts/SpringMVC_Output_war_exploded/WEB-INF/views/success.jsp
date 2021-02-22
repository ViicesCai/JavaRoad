<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/14
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>
    操作成功！<br>
    pageContext:${pageScope.msg}<br>
    request:${requestScope.msg}<br>
    session:${sessionScope.msg}<br>
    application:${applicationScope.msg}<br>
</body>
</html>
