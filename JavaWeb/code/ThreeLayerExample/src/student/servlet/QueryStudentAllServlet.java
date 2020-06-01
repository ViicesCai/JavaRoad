package student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

/**
 * 查询所有学生
 * 
 * @author CAI
 *
 */
public class QueryStudentAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		IStudentService service = new StudentServiceImpl();
		List<Student> students = service.queryAllStudnets();
		// 将学生信息放入 request 域
		request.setAttribute("students", students);
		
		// 通过请求转发传递数据，重定向会丢失 request 域
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
