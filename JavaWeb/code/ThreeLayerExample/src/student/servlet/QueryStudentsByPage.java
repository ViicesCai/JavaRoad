package student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.entity.Page;
import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

/**
 * 按页码查询学生
 * 
 * @author CAI
 *
 */
public class QueryStudentsByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService service = new StudentServiceImpl();
		int count = service.getTotalCount(); // 数据总数
		
		// 分页
		Page page = new Page();
		String requestPage = request.getParameter("currentPage");
		
		if (requestPage == null) { // 第一次访问
			requestPage = "0"; // 首页
		}
		
		int currentPage = Integer.parseInt(requestPage); // 当前页码
		int pageSize = 3; // 页面大小
		
		// 数据集合
		List<Student> students = service.queryStudentsByPage(currentPage, pageSize);
		
		page.setCurrentPage(currentPage);
		page.setTotalCount(count);
		page.setPageSize(pageSize);
		page.setStudents(students);
		
		request.setAttribute("p", page);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
