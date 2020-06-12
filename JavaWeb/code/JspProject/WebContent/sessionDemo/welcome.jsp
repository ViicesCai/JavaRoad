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
		String name = session.getAttribute("uname").toString();
	
		if(name != null) {
			out.println("欢迎您！" + name);
			Cookie cookie = new Cookie("uname", name);
			response.addCookie(cookie);
			
	%>
	
	<a href="invalidate.jsp">注销</a>
	
	<%		
		} else {
			
		}
	%>
</body>
</html>