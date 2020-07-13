/**
 * 
 */
package edu.fdzc.mapper;

import edu.fdzc.entity.Student;

/**
 * 学生表访问接口：StudentDao(这里为了和映射文件保持一致，改名为 mapper)
 * 
 * @author CAI
 *
 */
public interface StudentMapper {

	/**
	 * 添加学生
	 * 
	 * @param student
	 */
	public void addStudent(Student student);
	
	/**
	 * 根据学号查询学生
	 * 
	 * @param sno 学号
	 * @return
	 */
	public Student queryStudentBySno(int sno);
}
