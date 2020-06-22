/**
 * 
 */
package edu.fdzc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fdzc.entity.Student;
import edu.fdzc.service.IStudentService;

/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IStudentService studentService =  (IStudentService)context.getBean("studentService");
		studentService.addStudent(new Student(12, "CAI", 22));
	}
}
