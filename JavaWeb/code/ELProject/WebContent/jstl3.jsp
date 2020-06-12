<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	--- for循环 <br>
 	<c:forEach begin="0" end="5" step="1" varStatus="status">
 	${status.index}Hello <br>
	</c:forEach>
	
	--- 遍历集合 <br>
 	<c:forEach var="book" items="${requestScope.books}">
 	${book } <br>
	</c:forEach>
</body>
</html>