package student.dao;

import java.util.List;

import student.entity.Student;

public interface IStudentDao {
	
	/**
	 *学生是否存在
	 * @param sno
	 * @return boolean 是否存在
	 */
	public boolean isExist(int sno);
	
	/**
	 * 按照 sno 查找学生
	 * @param sno
	 * @return student
	 */
	public Student queryStudentBySno(int sno);
	
	/**
	 * 按照 sname 查找学生
	 * 
	 * @param sname
	 * @return Student 学生对象
	 */
	public Student queryStudentBySname(String sname);
	
	/**
	 * 按照 sage 查找学生
	 * 
	 * @param sage
	 * @return Student 学生对象
	 */
	public Student queryStudentBySage(int sage);
	
	/**
	 * 查找全部学生
	 * 
	 * @param sno
	 * @return List<Student> 学生集合
	 */
	public List<Student> queryAllStudents();
	
	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return boolean 执行结果
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 根据 sno 删除学生
	 * 
	 * @param sno
	 * @return boolean 执行结果
	 */
	public boolean deleteStudentBySno(int sno);
	
	/**
	 * 根据 sno 修改指定学生
	 * 
	 * @param sno
	 * @return boolean 执行结果
	 */
	public boolean updateStudentBySno(int sno, Student student);
	
	/**
	 * 返回数据查询总数
	 * 
	 * @return int 总数据量
	 */
	public int getTotalCount();
	
	/**
	 * 根据页码查询学生数据
	 * 
	 * @param currentPage 当前页（页码）
	 * @param pageSize 页面大小（每页显示的数据条数）
	 * @return List<Student> 学生集合
	 */
	public List<Student> queryStudentsByPage(int currentPage, int pageSize);
}
