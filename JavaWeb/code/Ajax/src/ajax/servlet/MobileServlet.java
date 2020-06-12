package ajax.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String mobile = request.getParameter("mobile");
		
		PrintWriter out = response.getWriter(); // 以输出流的方式发送验证消息
		if ("18888888888".equals(mobile)) { // 验证手机：假设此时数据库中仅包含该号码
			// out.write("true");
			out.write("{\"msg\":\"true\"}");

		} else {
			out.write("{\"msg\":\"false\"}");
		}
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
