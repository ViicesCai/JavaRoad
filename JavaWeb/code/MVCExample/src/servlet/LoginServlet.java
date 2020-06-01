package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import entity.Login;

/**
 * 登录控制器：接收 view 请求，并分发给 Model 进行处理
 * @author CAI
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理登录
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		// 封装登录对象：用户名、密码
		Login login = new Login(name, pwd);
		
		// 调用登录功能
		int result = LoginDao.login(login);
		
		if (result > 0) { // 登录成功
			response.sendRedirect("welcome.jsp");
			
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
