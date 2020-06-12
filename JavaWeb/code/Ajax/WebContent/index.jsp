<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
	function register() {
		var mobile = document.getElementById("mobile").value;
		
		// 通过 Ajax异步方式请求服务端
		xmlHttpRequest = new XMLHttpRequest();
		
		// 设置 xmlHttpRequest 的回调函数
		xmlHttpRequest.onreadystatechange = callBack;
		// 设置 get 方式
		// xmlHttpRequest.open("get", "MobileServlet?mobile=" + mobile, true);

		xmlHttpRequest.open("post", "MobileServlet", true);
		// 设置 get 方式不需要头信息
		// xmlHttpRequest.send(null); // 键值对

		// 设置 post 方式的头信息
		xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttpRequest.send("mobile=" + mobile); // 键值对
	}
	
	// 定义回调函数(接受服务端的返回值)
	function callBack() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			// 接收服务端返回的数据
			var data = xmlHttpRequest.responseText; // 服务端返回值为 String 格式
			
			if (data == "true") {
				alert("该号码已存在，请更换");
				
			} else {
				alert("注册成功");
			}
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<input id="mobile"> <br>
	<input type="button" value="注册"  onclick="register()">
</body>
</html>