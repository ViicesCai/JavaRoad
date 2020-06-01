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
 * 查询学生
 * 
 * @author CAI
 *
 */
public class QueryStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 接受前端传来的学号
		int sno = Integer.parseInt(request.getParameter("sno"));
		
		IStudentService service = new StudentServiceImpl();
		
		Student student = service.queryStudentBySno(sno);
		
		// 将学生对象放入 request 域
		request.setAttribute("student", student);
		request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
