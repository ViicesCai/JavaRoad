<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	----Request<br/>
	${requestScope.student.id} - ${requestScope.student.name}<br/>
	
	----Session<br/>
	${sessionScope.student.id} - ${sessionScope.student.name}<br/>
</body>
</html>