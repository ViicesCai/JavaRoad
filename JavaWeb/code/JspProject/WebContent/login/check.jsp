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
		request.setCharacterEncoding("UTF-8"); // 统一编码
		String name = request.getParameter("uname");
		String pwd =  request.getParameter("upwd");
		
		if("zs".equals(name) && "abc".equals(pwd)) {
			// response.sendRedirect("success.jsp"); // 页面跳转（重定向）
			// 此种方式会造成请求的数据丢失
			request.getRequestDispatcher("success.jsp").forward(request, response);
			// 请求转发，可以获取到数据，并且地址栏没有改变
			
		} else {
			out.println("登陆失败：用户名或密码错误！");
		}
	%>
</body>
</html>