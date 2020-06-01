package student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

/**
 * 增加学生
 * 
 * @author CAI
 *
 */
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8"); // 设置编码，防止乱码
		int no = Integer.parseInt(request.getParameter("sno")); // 学号
		String name = request.getParameter("sname"); // 学生姓名
		int age = Integer.parseInt(request.getParameter("sage")); // 学生年龄
		String address = request.getParameter("saddress"); // 学生地址
		
		// 封装成一个对象
		Student student = new Student(no, name, age, address);
		
		IStudentService service = new StudentServiceImpl();
		
		/**
		 * out、request、response、session、application
		 * out: PrintWriter out = response.getWriter();
		 * session: request.getSession();
		 * application: request.getServletContext();
		 */
		
		// 设置响应编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String result = "添加成功！";
		if (!service.addStudent(student)) { // 判断执行是否成功
			result = "添加失败！";
		}
		
		request.setAttribute("result", result); // 失败标识符	
		request.getRequestDispatcher("QueryStudentsByPage").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
