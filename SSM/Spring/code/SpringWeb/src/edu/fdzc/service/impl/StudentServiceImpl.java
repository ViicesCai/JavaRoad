/**
 * 
 */
package edu.fdzc.service.impl;

import edu.fdzc.dao.IStudentDao;
import edu.fdzc.dao.impl.StudentDaoImpl;
import edu.fdzc.service.IStudentService;

/**
 * @author CAI
 *
 */
public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao;
	
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public String queryStudentById() {
		return studentDao.queryStudentById();
	}	
}
