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
 * 修改学生
 * 
 * @author CAI
 *
 */
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 接受前端传来的学号
		int sno = Integer.parseInt(request.getParameter("sno"));
		
		// 接受修改的内容
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		
		// 将修改的内容封装
		Student student = new Student(name, age, address);
		IStudentService service = new StudentServiceImpl();
		
		// 设置响应编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String result = "修改成功！";
		if (!service.updateStudentBySno(sno, student)) {
			result = "修改失败！";
			
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("QueryStudentsByPage").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
