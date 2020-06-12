<%@page import="com.sun.media.sound.AlawCodec"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!
		public String info;
	
		public void init() {
			info = LocalDate.now() + "Hello ! this is my jsp";
		}
	%>
	
	<%
		String name = "JavaCode" + "<br>";
		out.print(name);
		init();
	%>
	
	<%="info：" + info + "<br>" %>
	
	<%= "当前项目的虚拟路径：" + application.getContextPath() + "<br>" %>
	<%= "当前项目的绝对路径：" + application.getRealPath("/JspProject") + "<br>" %>
</body>
</html>