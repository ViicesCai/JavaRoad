/**
 * 
 */
package edu.fdzc.service;

import edu.fdzc.entity.Student;

/**
 * ѧ�� �����
 * 
 * @author CAI
 *
 */
public interface IStudentService {
	void addStudent(Student student);
	
	void deleteStudentByNo(int sno);
}
