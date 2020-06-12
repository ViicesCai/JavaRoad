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
	--- 单重选择<br>
	<c:if test="${10 > 2 }" var="result" scope="request">
		结果为：${result}
	</c:if>
	
	--- 多重选择<br>
	<c:set var="role" value="学生"  scope="request"/>
	
	<c:choose>
		<c:when test="${requestScope.role == '老师' }">
		老师 code ...
		</c:when>
		
		<c:when test="${requestScope.role == '学生' }">
		学生 code ...
		</c:when>
		
		<c:when test="${requestScope.role eq '家长' }">
		家长 code ...
		</c:when>
		
		<c:otherwise>
		管理员 ...
		</c:otherwise>
	</c:choose>
</body>
</html>