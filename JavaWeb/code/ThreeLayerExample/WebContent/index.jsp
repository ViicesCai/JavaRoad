<%@page import="student.entity.Page"%>
<%@page import="student.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>

<script type="text/javascript">
	// 隔行变色
	$(document).ready(function() {
		$("tr:odd").css("background-color", "lightgray");
	});
	
	var = 
</script>
<meta charset="UTF-8">
<title>学生信息列表</title>
</head>
<body>
	<%
		String error = (String)request.getAttribute("result");
		
		if(error != null) {
	%>
			<script type="text/javascript">
			alert("<%= error %>");
			</script>
	<%
		}
	%>
	<a href="add.jsp">添加学生</a>

	<table border="1">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>地址</th>
			<th>操作</th>
		</tr>

		<%
			// 获取 request 域中的数据
			Page requestPage = (Page)request.getAttribute("p");

			for (Student student : requestPage.getStudents()) {
		%>
		<tr>
			<td><a href="QueryStudentServlet?sno=<%=student.getSno()%>"><%=student.getSno()%></a></td>
			<td><%=student.getSname()%></td>
			<td><%=student.getSage()%></td>
			<td><%=student.getSaddress()%></td>
			<td>
			<a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a>
			</td>
		</tr>

		<%
			}
 		%>
	</table>
	
	<%
		if(requestPage.getCurrentPage() == 0) { // 首页
			
	%>
	
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getCurrentPage() + 1 %>">下页</a> &nbsp;
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getTotalPage() - 1 %>">尾页</a> &nbsp;
	<%
		} else if(requestPage.getCurrentPage() == requestPage.getTotalPage() - 1) {
			
	%>
		<a href="QueryStudentsByPage?currentPage=0">首页</a> &nbsp;
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getCurrentPage() - 1 %>">上页</a> &nbsp;
		
	<%
		} else {
			
	%>
		<a href="QueryStudentsByPage?currentPage=0">首页</a> &nbsp;
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getCurrentPage() - 1 %>">上页</a> &nbsp;
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getCurrentPage() + 1 %>">下页</a> &nbsp;
		<a href="QueryStudentsByPage?currentPage=<%= requestPage.getTotalPage() - 1 %>">尾页</a> &nbsp;
	<% 
		}
	%>
</body>
</html>