package ajax.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajax.entity.Student;
import net.sf.json.JSONObject;

public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter(); // 以输出流的方式发送验证消息
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("前端传递来的值：" + name + "::" + age);
		
		Student stu1 = new Student();
		stu1.setName("Jack");
		stu1.setAge(23);
		
		Student stu2 = new Student();
		stu2.setName("Cai");
		stu2.setAge(22);
		
		Student stu3 = new Student();
		stu3.setName("Lily");
		stu3.setAge(19);
		
		JSONObject json = new JSONObject();
		json.put("stu1", stu1);
		json.put("stu2", stu2);
		json.put("stu3", stu3);
		
		out.print(json); // 返回JSON对象给客户端
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
