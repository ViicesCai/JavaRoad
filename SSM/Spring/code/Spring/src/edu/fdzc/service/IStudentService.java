/**
 * 
 */
package edu.fdzc.service;

import edu.fdzc.entity.Student;

/**
 * 学生 服务层
 * 
 * @author CAI
 *
 */
public interface IStudentService {
	void addStudent(Student student);
	
	void deleteStudentByNo(int sno);
}
