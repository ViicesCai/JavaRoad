package edu.fdzc.service.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.fdzc.service.IStudentService;

/**
 * Servlet implementation class QueryStudentByIdServlet
 */
public class QueryStudentByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentService service;
	
	/**
	 * Servlet 初始化方法：在初始化时，获取SpringIOC 容器的 Bean 对象
	 */
	@Override
	public void init() throws ServletException {
		// WEB 项目获取 Spring 上下文对象
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		// 在 Servlet 中 通过 getBean 获取 IOC 容器中的 bean
		service = (IStudentService)context.getBean("studentService");
		
	}
	
	public void setStudentService(IStudentService service) {
		this.service = service;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = service.queryStudentById();
		
		request.setAttribute("name", name);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
