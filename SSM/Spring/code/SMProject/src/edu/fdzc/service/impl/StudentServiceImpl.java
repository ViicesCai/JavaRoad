/**
 * 
 */
package edu.fdzc.service.impl;

import edu.fdzc.entity.Student;
import edu.fdzc.mapper.StudentMapper;
import edu.fdzc.service.IStudentService;

/**
 * 学生表服务实现类
 * 
 * @author CAI
 *
 */
public class StudentServiceImpl implements IStudentService {
	private StudentMapper studentMapper;
	
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	@Override
	public void addStudent(Student student) {
		// 调用 DAO
		studentMapper.addStudent(student);
	}
	
}
