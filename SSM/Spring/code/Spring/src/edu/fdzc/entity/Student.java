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
 * ѧ��ʵ����
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
	 * ���� name ѧϰ�γ�
	 * 
	 * @param name �γ���
	 */
	public void learn(String name) {
		// �ӹ����л�ȡ
		//ICourse course = CourseFactory.getCourse(name);
		//course.learn();
		
		// ֱ�Ӵ� IOC �����л�ȡ
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ICourse course = (ICourse) context.getBean(name);
		course.learn();
	}
	
	/**
	 * ѧϰ Java
	 */
	public void learnJava() {
		ICourse course = new JavaCourse();
		course.learn();
	}
	
	/**
	 * ѧϰ Html
	 */
	public void learnHtml() {
		ICourse course = new HtmlCourse();
		course.learn();
	}
}
