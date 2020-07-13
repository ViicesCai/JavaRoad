<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	当前学生：
	学号：${requestScope.student.sNo}
	姓名：${requestScope.student.sName}
	年龄：${requestScope.student.sAge}
</body>
</html>