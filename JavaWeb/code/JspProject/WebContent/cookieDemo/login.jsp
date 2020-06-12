<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		String uname; // 全局变量
	%>

	<%
		boolean flag = false;
		
		// 获取 cookie
		Cookie[] cookies = request.getCookies();
	
		for(Cookie cookie : cookies) {
			if("uname".equals(cookie.getName())) {
				uname = cookie.getValue();
				flag = true;
			}
		}
		
		if(!flag) {
			out.println("已失效");
			
		} else {
			out.println("cookie:" + uname);
		}
	%>
	
	<form action="check.jsp" method="post">
		用户名：<input type="text" name="uname" value="<%= (uname == null ? "":uname) %>"> <br>
		密码：<input type="password" name="upwd"> <br>
		<input type="submit" value="登陆"> <br>
	</form>
</body>
</html>