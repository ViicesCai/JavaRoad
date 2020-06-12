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
	<c:set var="name" value="Cai" scope="request"/>
	${requestScope.name } <br>
	
	--- 给对象赋值 <br>
	当前对象：${requestScope.student.name} <br>
	<c:set target="${requestScope.student}" property="name" value="ViicesCAI"/>
	当前对象：${requestScope.student.name} <br>
	
	--- 给 MAP 赋值 <br>
	${requestScope.country.cn} <br>
	<c:set target="${requestScope.country}" property="cn" value="中华人民共和国"/>
	${requestScope.country.cn} <br>
	
	--- 给不存在的变量赋值 <br>
	<c:set var="school" value="福州大学" scope="request" />
	${requestScope.school } <br>
	
	--- out:显示数据 <br>
	<c:out value="${requestScope.student}" /> <br>
	--- 显示不存在的数据 <br>
	<c:out value="${requestScope.stu}" default="HHH" /> <br>
	
	<a href="https:www.baidu.com">百度</a> <br>
	true:<c:out value='<a href="https:www.baidu.com">百度</a>' escapeXml="true" /> <br>
	false:<c:out value='<a href="https:www.baidu.com">百度</a>' escapeXml="false" /> <br>
	
	--- remove:删除数据 <br>
	<c:out value="${student.name }" default="被删除了" /> <br>
	<c:remove var="student" scope="request"/> 
	<c:out value="${student.name }" default="被删除了" /> <br>
</body>
</html>