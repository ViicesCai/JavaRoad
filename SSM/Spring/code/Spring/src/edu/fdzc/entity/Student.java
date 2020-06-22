/**
 * 
 */
package edu.fdzc.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fdzc.factory.CourseFactory;
import edu.fdzc.instance.HtmlCourse;
import edu.fdzc.instance.ICourse;
import edu.fdzc.instance.JavaCourse;

/**
 * 学生实体类
 * 
 * @author CAI
 *
 */
public class Student {
	private int sno;
	private String sname;
	private int sage;
	
	public Student() {
		super();
	}
	
	public Student(int sno, String sname, int sage) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sage = sage;
	}

	public int getSno() {
		return sno;
	}
	
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public int getSage() {
		return sage;
	}
	
	public void setSage(int sage) {
		this.sage = sage;
	}

	@Override
	public String toString() {
		return "[sno=" + sno + ", sname=" + sname + ", sage=" + sage + "]";
	}
	
	/**
	 * 根据 name 学习课程
	 * 
	 * @param name 课程名
	 */
	public void learn(String name) {
		// 从工厂中获取
		//ICourse course = CourseFactory.getCourse(name);
		//course.learn();
		
		// 直接从 IOC 容器中获取
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ICourse course = (ICourse) context.getBean(name);
		course.learn();
	}
	
	/**
	 * 学习 Java
	 */
	public void learnJava() {
		ICourse course = new JavaCourse();
		course.learn();
	}
	
	/**
	 * 学习 Html
	 */
	public void learnHtml() {
		ICourse course = new HtmlCourse();
		course.learn();
	}
}
