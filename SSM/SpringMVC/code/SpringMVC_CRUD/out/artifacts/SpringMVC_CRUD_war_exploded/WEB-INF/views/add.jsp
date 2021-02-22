<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/16
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
</head>
<body>
    <h1>添加员工</h1>
    <!--
        表单标签
        通过 SpringMVC 的表单标签可以实现将模型数据中的属性和HTML表单元素相绑定，
        以实现数据更便捷编辑和表单值的回显
        SpringMVC认为，表单数据中的每一项最终都是要回显的；
        path指定的是一个属性，这个属性是从隐含模型（请求域中取出的某个对象的属性）
        path指定的每一个属性，请求域中必须有一个对象拥有这个属性
            请求域 = command
            modelAttribute:
                以前我们表单标签会从请求域中获取一个command对象；把这个对象中的每一个属性对应的显示
                可以告诉SpringMVC不要取command的值，而是从modelAttribute中取值
                取对象所用的key由modelAttribute指定
     -->
    <%
        pageContext.setAttribute("ctp", request.getContextPath());
    %>
    <form:form action="${ctp}/emp" modelAttribute="employee" method="post">
        <!--
            path:对应html input标签的name
                1.当作原生的name属性
                2.自动回显隐含模型中某个对象对应的属性的值
        -->
        <!-- form:errors：可以自动显示字段的错误信息 -->
        <!-- <form:errors path="lastName"/> -->
        lastName:<form:input path="lastName"/> ${errorInfo.lastName} <br>
        email:<form:input path="email"/> ${errorInfo.email} <br>
        gender: <br>
        男：<form:radiobutton path="gender" value="1"/> <br>
        女：<form:radiobutton path="gender" value="0"/> <br>
        birth:<form:input path="birth"/> <form:errors path="birth"/> <br>
        dept:
        <!--
            item：指定要遍历的集合：遍历出的每一个元素是一个department对象
            itemLabel：属性名，指定遍历出的这个对象的哪个属性作为option标签体的值
            itemValue：属性名，指定遍历出的这个对象的哪个属性作为option要提交的value值
        -->
        <form:select path="department.id" items="${depts}" itemLabel="departmentName"
                     itemValue="id">
        </form:select>
        <br>

        <input type="submit" value="保存">
    </form:form>

    <!-- Employee() -->
<%--    <form action="">--%>
<%--        lastName：<input type="text" name="lastName"> <br>--%>
<%--        email：<input type="text" name="email"> <br>--%>
<%--        gender：<br>--%>
<%--            男<input type="radio" name="gender" value="1"> <br>--%>
<%--            女<input type="radio" name="gender" value="0"> <br>--%>
<%--        dept：--%>
<%--        <select name="department.id">--%>
<%--            <c:forEach items="${depts}" var="deptItem">--%>
<%--                <option value="${deptItem.id}">${deptItem.departmentName}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br>--%>
<%--        <input type="submit" value="提交">--%>
<%--    </form>--%>
</body>
</html>
