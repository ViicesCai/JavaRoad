/**
 * 
 */
package edu.fdzc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import edu.fdzc.entity.Student;
import edu.fdzc.mapper.StudentMapper;

/**
 * 学生表访问实现类
 * 
 * @author CAI
 *
 */
public class StudentDaoImpl extends SqlSessionDaoSupport implements StudentMapper {

	@Override
	public void addStudent(Student student) {
		SqlSession session = super.getSqlSession();
		StudentMapper studentDao = session.getMapper(StudentMapper.class);
		studentDao.addStudent(student);
	}
	
}
