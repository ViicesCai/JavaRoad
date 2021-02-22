<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/2/12
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
  </head>
  <body>
    <a href="handle?username=CAI">handle</a> <br>
    <a href="handle2">handle2</a> <br>
    <a href="handle3">handle3</a> <br>

    <p>传入一个POJO类</p>
    <form action="book" method="post">
      书名：<input type="text" name="bookName"> <br>
      作者：<input type="text" name="author"> <br>
      价格：<input type="text" name="price"> <br>
      库存：<input type="text" name="stock"> <br>
      销量：<input type="text" name="sales"> <br>
      <hr>

      省：<input type="text" name="address.province">
      市：<input type="text" name="address.city">
      街道：<input type="text" name="address.street"> <br>
      <input type="submit">
    </form>
  </body>
</html>
