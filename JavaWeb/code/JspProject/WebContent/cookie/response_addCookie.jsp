<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 服务端增加 cookie
		Cookie uname = new Cookie("name", "zs");
		Cookie upwd = new Cookie("pwd", "abc");
		
		response.addCookie(uname);
		response.addCookie(upwd);
		
		// 页面跳转到客户端
		response.sendRedirect("result.jsp");
	%>
</body>
</html>