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
			// 分配 session 值：只有登录成功 才会存在 uname/upwd
			session.setAttribute("uname", name);
			session.setAttribute("upwd", pwd);
			
			System.out.println("SessionID:" + session.getId());
			
			session.setMaxInactiveInterval(5);
			
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			
		} else {
			// 登录失败
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>