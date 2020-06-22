package edu.fdzc.dao;

import edu.fdzc.entity.Student;

/**
 * 学生 DAO
 * 
 * @author CAI
 *
 */
public interface IStudentDao {
	/**
	 * 增加学生
	 * 
	 * @param student
	 */
	public void addStudent(Student student);
}
