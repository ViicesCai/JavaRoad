/**
 * 
 */
package edu.fdzc.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.fdzc.dao.IStudentDao;
import edu.fdzc.dao.impl.StudentDaoImp;
import edu.fdzc.entity.Student;
import edu.fdzc.service.IStudentService;

/**
 * ѧ�� �����ʵ����
 * 
 * @author CAI
 *
 */
@Service("studentService")
public class StudentServiceImpl implements IStudentService {
	@Autowired // �Զ�װ�䣺byType
	// @Qualifier("xxx")��byName
	IStudentDao studentDao;

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,
			rollbackFor = {SQLException.class, ArithmeticException.class})
	public void addStudent(Student student) {
		studentDao = null;
		studentDao.addStudent(student);
	}

	@Override
	public void deleteStudentByNo(int sno) {
		System.out.println("ɾ��ѧ��...");
	}
}
