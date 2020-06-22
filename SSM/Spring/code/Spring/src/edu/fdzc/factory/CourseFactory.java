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
 * �γ̹���
 * 
 * @author CAI
 *
 */
public class CourseFactory {
	
	/**
	 * �������ֻ�ȡ�γ�
	 * 
	 * @param name
	 * @return �γ̶���
	 */
	public static ICourse getCourse(String name) {
		if ("java".equals(name)) {
			return new JavaCourse();
			
		} else {
			return new HtmlCourse();
		}
	}
	
	/**
	 * �������ִ� IOC�����л�ȡ�γ�
	 * @param name
	 * @return 
	 */
	public static ICourse getCourseWithIOC(String name) {
		// ��ȡ IOC ����
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		if ("java".equals(name)) {
			return (ICourse)context.getBean("javaCourse");
			
		} else {
			return (ICourse)context.getBean("htmlCourse");
		}
	}
}
