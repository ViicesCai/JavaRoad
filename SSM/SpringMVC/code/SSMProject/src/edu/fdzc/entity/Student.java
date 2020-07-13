/**
 * 
 */
package edu.fdzc.entity;

/**
 * 学生数据表
 * 
 * @author CAI
 *
 */
public class Student {
	private int sNo;
	private String sName;
	private int sAge;
	
	public Student() {
		super();
	}
	
	public Student(int sNo, String sName, int sAge) {
		super();
		this.sNo = sNo;
		this.sName = sName;
		this.sAge = sAge;
	}
	
	public int getsNo() {
		return sNo;
	}
	
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	
	public String getsName() {
		return sName;
	}
	
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	public int getsAge() {
		return sAge;
	}
	
	public void setsAge(int sAge) {
		this.sAge = sAge;
	}
	
}
