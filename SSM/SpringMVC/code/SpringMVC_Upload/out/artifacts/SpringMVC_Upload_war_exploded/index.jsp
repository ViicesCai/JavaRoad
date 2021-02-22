<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/20
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
    <!--
        1.准备上传表单：enctype=multipart/form-data
        2.导入commons-fileupload.jar 和 commons-io.jar
        3.在SpringMVC配置文件中配置文件上传解析器(MultipartResolver)
        4.文件上传请求处理
            在处理器方法上写一个 @RequestParam(value = "headerimg") MultipartFile file
            封装当前文件的信息，可以直接保存
     -->
    ${msg}
    <form action="${ctp}/upload" method="post" enctype="multipart/form-data">
      用户头像：<input type="file" name="headerimg"> <br>
      用户名：<input type="text" name="username"> <br>
      <input type="submit" value="提交">
    </form>

    ${msg}
    <p>多文件上传</p>
    <form action="${ctp}/uploadFiles" method="post" enctype="multipart/form-data">
      上传文件：<input type="file" name="headerfile"> <br>
      上传文件：<input type="file" name="headerfile"> <br>
      上传文件：<input type="file" name="headerfile"> <br>
      上传文件：<input type="file" name="headerfile"> <br>
      <input type="submit" value="提交">
    </form>
  </body>
</html>
