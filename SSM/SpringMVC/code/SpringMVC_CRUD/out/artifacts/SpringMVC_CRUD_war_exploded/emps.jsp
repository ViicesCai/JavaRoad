<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/20
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
<head>
    <title>Ajax获取员工信息</title>
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
    <a href="${ctp}/ajax/getallemp">ajax获取所有员工信息</a>
    <div>

    </div>
    <script type="text/javascript">
        $("a:first").click(function () {
            // 发送ajax请求获取所有员工信息
            $.ajax({
                url : "${ctp}/ajax/getallemp",
                type : "GET",
                success : function (data) {
                    $.each(data, function () {
                        var empInfo = this.lastName + "-->" + this.birth + "-->" + this.gender;

                       $("div").append(empInfo + "<br>");
                    });
                }
            });

            return false;
        });
    </script>
</body>
</html>
