/**
 * 
 */
package edu.fdzc.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.fdzc.dao.IStudentDao;
import edu.fdzc.entity.Student;

/**
 * ѧ�� ���ʲ�ʵ����
 * 
 * @author CAI
 *
 */
//@Component("studentDao")
@Repository
public class StudentDaoImp implements IStudentDao {
	
	public void addStudent(Student student) {
		System.out.println("����ѧ��...");
	}
}
