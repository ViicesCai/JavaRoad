<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#testJson").click(function(){
			// 通过 Ajax 请求 SpringMVC
			$.post(
				"handler/testJson", // 服务器地址
				// {"name" : "cai"}, // 传递参数

				function(result){ // 服务端处理之后的回调函数：返回 List<Student>
					for(var i = 0; i < result.length; i++){
						alert(result[i].id +  " - " + result[i].name);
					}
				}
			);
		});
	});
</script>
</head>
<body>

	<input type="button" value="testJson" id="testJson" /> <br/>
	<a href="welcome">First SpringMVC</a> <br/>
	<a href="getName/cai">Name</a> <br/>
	
	<br/>
	<form action="handler/testRest/1234" method="post">
		<input type="submit" value="增">
	</form>
	<br/>
	
	<form action="handler/testRest/1234" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="删">
	</form>
	<br/>
	
	<form action="handler/testRest/1234" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="改">
	</form>
	<br/>
	
	<form action="handler/testRest/1234" method="get">
		<input type="submit" value="查">
	</form>
	<br/>
	
 	<form action="handler/testParam" method="get">
		<input name="uname" type="text">
		<input type="submit" value="获取">
	</form>
	<br/>
	
	<a href="handler/testRequestHeader">testRequestHeader</a><br/>
	<a href="handler/testCookieValue">testCookieValue</a><br/>
	
	<form action="handler/testObjectProperties" method="post">
		id:<input name="id" type="text">
		name:<input name="name" type="text">
		homeaddress:<input name="address.homeAddress" type="text">
		schooladdress:<input name="address.schoolAddress" type="text">
		<input type="submit" value="提交">
	</form>
	<br/>
	
	<a href="handler/testServletAPI">ServletAPI</a> <br/>
	<a href="handler/testModelAndView">ModelAndView</a> <br/>
	
	<form action="handler/testModelAttribute" method="post">
		编号:<input name="id" type="hidden" value="30"> <br/>
		姓名:<input name="name" type="text" > <br/>
		<input type="submit" value="修改">
	</form>

	<a href="handler/testI18n">国际化</a> <br>
	<a href="handler/testMvcViewController">testMvcViewController</a> <br>
	
	<form action="handler/testConverter" method="post">
		学生信息：<input name="studentInfo" type="text"> <br>
		<input type="submit" value="转换"> <br>
	</form>
	
	<form action="handler/testDateTimeFormat" method="post">
		编号：<input name="id" type="text"> <br>
		姓名：<input name="name" type="text"> <br>
		出生日期：<input name="birthday" type="text"> <br>
		<input type="submit" value="提交"> <br>
	</form>
	
	<form action="handler/testUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <br>
		描述：<input name="desc" type="text" /> <br>
		<input type="submit" value="上传" /> <br>
	</form>
	
	<a href="handler/testInterceptor">testInterceptor</a> <br>
	
	<a href="handler/testExceptionHandler">数学异常</a> <br>
	<a href="handler/testExceptionHandler2">数组越界</a> <br>
	
</body>
</html>