/**
 * 
 */
package edu.fdzc.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fdzc.instance.HtmlCourse;
import edu.fdzc.instance.ICourse;
import edu.fdzc.instance.JavaCourse;

/**
 * 课程工厂
 * 
 * @author CAI
 *
 */
public class CourseFactory {
	
	/**
	 * 根据名字获取课程
	 * 
	 * @param name
	 * @return 课程对象
	 */
	public static ICourse getCourse(String name) {
		if ("java".equals(name)) {
			return new JavaCourse();
			
		} else {
			return new HtmlCourse();
		}
	}
	
	/**
	 * 根据名字从 IOC容器中获取课程
	 * @param name
	 * @return 
	 */
	public static ICourse getCourseWithIOC(String name) {
		// 获取 IOC 容器
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		if ("java".equals(name)) {
			return (ICourse)context.getBean("javaCourse");
			
		} else {
			return (ICourse)context.getBean("htmlCourse");
		}
	}
}
