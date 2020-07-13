/**
 * 
 */
package edu.fdzc.service.impl;

import edu.fdzc.entity.Student;
import edu.fdzc.mapper.StudentMapper;
import edu.fdzc.service.StudentService;

/**
 * 学生 Service 实现类
 * 
 * @author CAI
 *
 */
public class StudentServiceImpl implements StudentService {
	// Service 依赖于 Dao(Mapper)
	private StudentMapper studentMapper;
	
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	public Student queryStudentBySno(int sno) {
		return studentMapper.queryStudentBySno(sno);
	}
	
}
