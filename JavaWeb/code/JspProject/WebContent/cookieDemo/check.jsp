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
		
		// 将用户名加入到 cookie 中
		Cookie cookieName = new Cookie("uname", name);
		cookieName.setMaxAge(1); // 设置有效时间
		response.addCookie(cookieName);

		response.sendRedirect("result.jsp");
	%>
</body>
</html>