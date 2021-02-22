package edu.fdzc.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class Employee {

	public Employee() {
	}

	public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	private Integer id;

	@NotEmpty
	@Length(min = 6, max = 18) // 最短6位，最长18位
	private String lastName;

	@Email
	private String email;

	//1 male, 0 female
	private Integer gender;

	// 规定页面日期提交的格式
	// @Path：必须是一个过去的时间
	// @Future：必须是一个未来的时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birth = new Date();

	@NumberFormat(pattern = "#,###.##")
	private Double salary;

	@JsonIgnore
	private Department department;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", gender=" + gender +
				", birth=" + birth +
				", department=" + department +
				'}';
	}
}
