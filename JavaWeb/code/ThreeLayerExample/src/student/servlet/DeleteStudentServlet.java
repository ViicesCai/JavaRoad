package student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

/**
 * 删除学生
 * 
 * @author CAI
 *
 */
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 接受前端传来的学号
		int sno = Integer.parseInt(request.getParameter("sno"));
		IStudentService service = new StudentServiceImpl();
		
		// 设置响应编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String result = "删除成功！";
		if (!service.deleteStudentBySno(sno)) {
			result = "删除失败！";
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("QueryStudentsByPage").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
