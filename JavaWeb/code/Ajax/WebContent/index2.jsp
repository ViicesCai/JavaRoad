<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" src="js/jquery-1.8.3/jquery.js"></script>

		<script type="text/javascript">
			function register() {
				var $mobile = $("#mobile").val();

				// $.ajax({
				// 	url:"MobileServlet",
				// 	请求方式:"post",
				// 	data:"mobile=" + $mobile,
				// 	success:function(result, testStatus) {
				// 		console.log(result);

				// 		if (result == "true") {
				// 			alert("已存在！注册失败！");

				// 		} else{
				// 			alert("注册成功！");
				// 		}
				// 	}, error:function(xhr, errorMessage, e) {
				// 		alert("系统异常！");
				// 	}
				// });

				// $.post(
				// 	"MobileServlet",
				// 	"mobile=" + $mobile,

				// 	function(result) {

				// 		if (result == "true") {
				// 			alert("已存在！注册失败！");

				// 		} else {
				// 			alert("注册成功！");
				// 		}
				// 	},
					
				// 	"text" // 预期返回值类型：xml 或 json 或 text(可以省略)
				// );
				
				// $("#tip").load(
				// 	"MobileServlet",
				// 	"mobile=" + $mobile,
				// );
				
				$.getJSON(
					"MobileServlet",
					{"mobile":$mobile},
					
					function(result) { // {"msg":"true|false"}
						if(result.msg == "true") {
							alert("已存在！注册失败！");
							
						} else {
							alert("注册成功！");
						}
					}
				);
			}
			
			// Json 中只有单个对象
			// function testJson() {
			// 	$.getJSON(
			// 		"JsonServlet",
			// 		{"name":"zs", "age":24},
					
			// 		function(result) {
			// 			// js 通过 eval() 将返回值转为一个 js 能识别的 json 对象
			// 			var jsonStudent = eval(result.stu1);
			// 			alert(jsonStudent.name + " " + jsonStudent.age);
			// 		}
			// 	);
			// }
			
			// Json中有多个对象
			function testJson() {
				$.getJSON(
					"JsonServlet",
					{"name":"Cai", "age":24},
					
					function(result) {
						// js 通过 eval() 将返回值转为一个 js 能识别的 json 对象
						var jsonStudents = eval(result); // Json 集合
						
						$.each(jsonStudents, function(i, element) {
							alert(this.name + " " + this.age);
						});
					}
				);
			}
		</script>
		<title>Insert title here</title>
	</head>
	<body>
		<input id="mobile"> <br>
		<input type="button" value="注册" onclick="register()"> <br>
		<input type="button" value="测试json" onclick="testJson()"> <br>
	</body>
</html>
