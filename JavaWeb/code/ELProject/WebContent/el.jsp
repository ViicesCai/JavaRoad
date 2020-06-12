<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${requestScope.info} <br>
	${requestScope["info"]} <br>
	
	<!-- 数组 -->
	${requestScope.books[0]} <br>
	${requestScope.books[2]} <br>
	
	<!-- map -->
	${requestScope.country.cn } <br>
	${requestScope.country["us"] } <br>
	
	<!-- 运算 -->
	-- 运算 -- <br>
	${3 > 2} , ${3 gt 2} <br>
	${3 > 2 || 3 < 2} , ${3 > 2 or 3 < 2} <br>
	
	<!-- empty:值为 null 或 不存在 为 true -->
	-- 不为空 -- <br>
	${empty requestScope["info"]} <br>
	
	-- 为空 -- <br>
	${empty requestScope["null"]} <br>
</body>
</html>