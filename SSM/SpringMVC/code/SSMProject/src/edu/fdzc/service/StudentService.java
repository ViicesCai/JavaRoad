/**
 * 
 */
package edu.fdzc.service;

import edu.fdzc.entity.Student;

/**
 * 学生 Service 接口
 * 
 * @author CAI
 *
 */
public interface StudentService {
	Student queryStudentBySno(int sno);
}
