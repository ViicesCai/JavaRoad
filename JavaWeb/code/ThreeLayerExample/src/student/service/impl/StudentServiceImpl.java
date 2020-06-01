/**
 * 
 */
package student.service.impl;

import java.util.List;

import student.dao.IStudentDao;
import student.dao.impl.StudentDaoImpl;
import student.entity.Student;
import student.service.IStudentService;

/**
 * 业务逻辑层
 * 
 * @author CAI
 *
 */
public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao = new StudentDaoImpl();
	
	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return boolean 执行结果
	 */
	public boolean addStudent(Student student) {
		
		if (!studentDao.isExist(student.getSno())) { // 该学生是否存在
			studentDao.addStudent(student);
			return true;
			
		} else {
			System.out.println("该学生已存在！");
			return false;
		}
	}
	
	/**
	 * 按 sno 删除学生
	 * 
	 * @param sno 学号
	 * @return boolean 执行结果
	 */
	public boolean deleteStudentBySno(int sno) {
		if (studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno);
		}
		
		return false;
	}
	
	/**
	 * 按 sno 修改学生
	 * @param sno
	 * @return boolean 执行结果
	 */
	public boolean updateStudentBySno(int sno, Student student) {
		if (studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno, student);
		}
		
		return false;
	}
	
	/**
	 * 根据 sno 查询学生
	 * @param sno
	 * @return Student
	 */
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	
	/**
	 * 查询所有学生
	 * @return List<Student>
	 */
	public List<Student> queryAllStudnets() {
		return studentDao.queryAllStudents();
	}

	
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		return studentDao.queryStudentsByPage(currentPage, pageSize);
	}
	
	public int getTotalCount() {
		return studentDao.getTotalCount();
	}
}
