<%--
  Created by IntelliJ IDEA.
  User: CAI
  Date: 2021/1/19
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
  pageEncoding="UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <script src="lib/jquery1.7.2.js"></script>
  <%
    pageContext.setAttribute("ctp", request.getContextPath());
  %>
  <body>
  <a href="ajax">
      ajax
  </a>
  <div id="display_student">

  </div>
  </body>
<script>
  $("a[href='ajax']").click(function () {
    // 项目中直接绝对路径，带项目名
    $.ajax({
      url: "${ctp}/ajax",
      type: "POST",
      dataType: "JSON",
      success: function (data) {
        //console.log(data);

        // 第一种遍历方法
        // $(data).each(function() {
        //   // this:代表遍历对象
        //   alert(this.name);
        // });

        // 第二种遍历方法
        // (obj, [callback])：第一个传入要遍历的数据，第二个传入回调函数
        // 回调函数可以接收两个参数;第一个参数为当前遍历的元素的索引,第二个参数代表当前的元素
        $("#display_student").empty();
        $.each(data, function (index, item) {
          // alert("[第" + index + "个]" + item.name);
          $("#display_student")
                  .append("<br/>学生姓名:" + item.name)
                  .append("<br/>学生年龄:" + item.age)
                  .append("<br/>学生邮箱:" + item.email)
                  .append("<hr/>");
        });
      }
    });

    return false; // 取消默认行为：不跳转
  });
</script>
</html>
