package el.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.servlet.Student;

public class ELInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "Hello,BeautifulWorld!";
		String[] books = {"设计模式", "数据结构与算法", "EffectiveJava"};
		
		Map<String, String> country = new HashMap<String, String>();
		country.put("cn", "中国");
		country.put("us", "美国");
		
		Student student = new Student("Cai", 22);
		
		request.setAttribute("info", str);
		request.setAttribute("books", books);
		request.setAttribute("country", country);
		request.setAttribute("student", student);
		
		request.getRequestDispatcher("jstl3.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
