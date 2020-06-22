/**
 * 
 */
package edu.fdzc.entity;

/**
 * �γ���
 * 
 * @author CAI
 *
 */
public class Course {
	private String cname;
	private int chour;
	private Teacher teacher; // �ڿ���ʦ�������� Teacher
	
	public Course() {
		super();
	}
	
	public Course(String cname, int chour, Teacher teacher) {
		super();
		this.cname = cname;
		this.chour = chour;
		this.teacher = teacher;
	}

	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public int getChour() {
		return chour;
	}
	
	public void setChour(int chour) {
		this.chour = chour;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "[cname=" + cname + ", chour=" + chour + ", teacher=" + teacher.getTname() + "]";
	}
}
