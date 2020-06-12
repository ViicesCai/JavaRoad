<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	登陆成功！<br>
	欢迎您：
	<%
		String name = request.getParameter("uname");
		out.println(name);
	%>
</body>
</html>