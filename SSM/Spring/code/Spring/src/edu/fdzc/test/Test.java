/**
 * 
 */
package edu.fdzc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fdzc.entity.AllCollectionType;
import edu.fdzc.entity.Course;
import edu.fdzc.entity.Student;
import edu.fdzc.factory.CourseFactory;
import edu.fdzc.instance.ICourse;
import edu.fdzc.service.IStudentService;

/**
 * ������
 * 
 * @author CAI
 *
 */
public class Test {
	public static void learnCourse() {
		Student student = new Student();
		student.learnJava();
		student.learnHtml();
	}
	
	public static void learnCourseWithFactory() {
		Student student = new Student();
		student.learn("java");
		
	}
	
	public static void learnCourseWithIOC() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// �� AOC ��ȡѧ��
		Student student = (Student)context.getBean("student");
		
		student.learn("htmlCourse");
	}
	
	public static void getCourseInfoFromIOC() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Course course = (Course)context.getBean("course");
		System.out.println(course.toString());
	}
	
	public static void collectionDemo() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AllCollectionType type = (AllCollectionType)context.getBean("collectionDemo");
		
		System.out.println(type.toString());
	}
	
	public static void testAnnotationAutoWired() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IStudentService service = (IStudentService)context.getBean("studentService");
		service.addStudent(new Student());
	}
	
	public static void testAop() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IStudentService service = (IStudentService)context.getBean("studentService");
		service.addStudent(new Student(211906601, "Cai", 22));
		// service.deleteStudentByNo(12);
		
	}
	
	public static void main(String[] args) {
//		// Spring �����Ķ���
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		Student student = (Student)context.getBean("student");
//		System.out.println(student.toString());
//		// 1.ʡ�� new
//		// 2.ʡ�Զ���ֵ
		
		// learnCourse();
		// learnCourseWithFactory();
		// learnCourseWithIOC();
		
		// getCourseInfoFromIOC();
		// collectionDemo();
		// testAnnotationAutoWired();
		testAop();
	}
}
