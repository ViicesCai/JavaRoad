/**
 * 
 */
package edu.fdzc.entity;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * 学生类
 * 
 * @author CAI
 *
 */
public class Student {
	@NumberFormat(pattern = "###,#") // 格式化
	private int id;
	private String name;
	private Address address;
	
	@Past // 当前时间以前
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 格式化：前台传递来的数据，将前台传递来的数据固定为 yyyy-MM-dd
	private Date birthday;
	
	public Student() {
		super();
	}
	
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
}
