<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/16
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
<head>
    <title>员工列表</title>
    <script type="text/javascript" src="${ctp}/js/jquery.js"></script>
</head>
<body>
    <h1>员工列表</h1>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>lastName</th>
                <th>email</th>
                <th>gender</th>
                <th>birth</th>
                <th>departmentName</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${emps}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender == 0 ? "女":"男"}</td>
                    <td>${emp.birth}</td>
                    <td>${emp.department.departmentName}</td>
                    <td>
                        <a href="${ctp}/emp/${emp.id}">edit</a>
                    </td>
                    <td>
                        <a href="${ctp}/emp/${emp.id}" class="delBtn">delete</a>
                    </td>
                </tr>
            </c:forEach>
       </tbody>
    </table>

    <a href="${ctp}/toaddpage">添加员工</a>

    <form action="${ctp}/quickAdd" method="post">
        <input name="empinfo" value="empAdmin-admin@qq.com-1-101"> <br>
        <input type="submit" value="快速添加"> <br>
    </form>

    <form id="deleteForm" action="${ctp}/emp/${employee.id}" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>
    <script type="text/javascript">
        $(function () {
            $(".delBtn").click(function () {
                if (confirm("确定删除？")) {
                // 改变表单的action指向
                $("#deleteForm").attr("action", this.href);

                // 提交表单
                $("#deleteForm").submit();
                }

                // 禁止默认行为
                return false;
            });
        });
    </script>
</body>
</html>
