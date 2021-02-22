<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/20
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
<head>
    <title>其他测试</title>
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
    <p>测试请求体</p>
    <form action="${ctp}/ajax/testRequestBody" method="post" enctype="multipart/form-data">
        <input type="text" name="username" value="tomcat"> <br>
        <input type="text" name="password" value="123456"> <br>
        <input type="file" name="file"> <br>
        <input type="submit" value="提交">
    </form>

    <a href="${ctp}/ajax/testRequestBody">ajax发送json数据</a>

    <p>测试请求体:test01</p>
    <form action="${ctp}/ajax/test01" method="post" enctype="multipart/form-data">
        <input type="text" name="username" value="tomcat"> <br>
        <input type="text" name="password" value="123456"> <br>
        <input type="file" name="file"> <br>
        <input type="submit" value="提交">
    </form>

    <p>测试请求体:test02</p>
    <form action="${ctp}/ajax/test02" method="post" enctype="multipart/form-data">
        <input type="text" name="username" value="tomcat"> <br>
        <input type="text" name="password" value="123456"> <br>
        <input type="file" name="file"> <br>
        <input type="submit" value="提交">
    </form>

</body>
    <script type="text/javascript">
        $("a:first").click(function () {
           var emp = {
               lastName : "张三",
               email : "123@qq.com",
               gender : 0
           };

           //alert(typeof emp);

           var empStr = JSON.stringify(emp);

           //alert(typeof empStr);

           $.ajax({
               url : '${ctp}/ajax/testRequestBody',
               type : "POST",
               data : empStr,
               contentType : "application/json",
               success : function (data) {
                   alert(data);
               }
           });

            return false;
        });
    </script>
</html>
