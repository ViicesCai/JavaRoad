<%@page import="jdbc.entity.Login"%>
<%@page import="jdbc.dao.LoginDao"%>
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
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		
		Login login = new Login(name, pwd);
		int result = LoginDao.login(login);
		
		if(result > 0) {
			out.println("登陆成功");
			
		} else if(result == 0){
			out.println("登陆失败,账号或密码错误");
			
		} else {
			out.println("系统异常");
		}
	%>
</body>
</html>