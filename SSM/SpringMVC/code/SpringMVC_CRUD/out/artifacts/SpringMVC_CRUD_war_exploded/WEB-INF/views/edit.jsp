<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/17
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
<head>
    <title>修改员工信息</title>
</head>
<body>
    <!-- 表单中的信息绑定为employee对象 -->
    <form:form action="${ctp}/emp/${emp.id}" modelAttribute="emp" method="post">
        <input type="hidden" name="_method" value="put">
        <input type="hidden" name="id" value="${emp.id}">

        lastName:${emp.lastName} <br>
        email:<form:input path="email"/> <br>
        gender:
        男<form:radiobutton path="gender" value="1"/>
        女<form:radiobutton path="gender" value="0"/> <br>
        dept:
        <form:select path="department.id" items="${depts}"
                     itemLabel="departmentName" itemValue="id"/> <br>
        <input type="submit" value="修改"/>

    </form:form>
</body>
</html>
