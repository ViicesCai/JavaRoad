package student.service;

import java.util.List;

import student.entity.Student;

public interface IStudentService {
	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return boolean 执行结果
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 按 sno 删除学生
	 * 
	 * @param sno 学号
	 * @return boolean 执行结果
	 */
	public boolean deleteStudentBySno(int sno);
	/**
	 * 按 sno 修改学生
	 * 
	 * @param sno
	 * @return boolean 执行结果
	 */
	public boolean updateStudentBySno(int sno, Student student);
	/**
	 * 根据 sno 查询学生
	 * 
	 * @param sno
	 * @return Student 学生对象
	 */
	public Student queryStudentBySno(int sno);
	
	/**
	 * 查询所有学生
	 * 
	 * @return List<Student> 学生集合
	 */
	public List<Student> queryAllStudnets();
	
	/**
	 * 查询当前页面的学生
	 * 
	 * @param currentPage 当前页
	 * @param pageSize 页面大小
	 * @return List<Student> 学生集合
	 */
	public List<Student> queryStudentsByPage(int currentPage, int pageSize);
	
	/**
	 * 返回数据查询总数
	 * 
	 * @return int 总数据量
	 */
	public int getTotalCount();
}
