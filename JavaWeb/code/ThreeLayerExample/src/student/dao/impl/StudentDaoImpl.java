/**
 * 
 */
package student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.utils.DBUtils;
import student.dao.IStudentDao;
import student.entity.Student;

/**
 * 数据访问层
 * 
 * @author CAI
 *
 */
public class StudentDaoImpl implements IStudentDao {
	
	/**
	 *学生是否存在
	 * @param sno
	 * @return boolean
	 */
	public boolean isExist(int sno) {
		return queryStudentBySno(sno) == null ? false:true;
	}
	
	/**
	 * 按照 sno 查找学生
	 * 
	 * @param sno
	 * @return Student 学生对象
	 */
	public Student queryStudentBySno(int sno) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			String sql = "Select * From student where sno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				
				student =  new Student(no, name, age, address);
				
				return student;
			}
			
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}
	
	/**
	 * 按照 sname 查找学生
	 * 
	 * @param sno
	 * @return Student 学生对象
 	 */
	public Student queryStudentBySname(String sname) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			String sql = "Select * From student where sname = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sname);
			rs = ps.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				
				student = new Student(no, name, age, address);
			}
			
			return student;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}
	
	/**
	 * 按照 sage 查找学生
	 * 
	 * @param sno
	 * @return Student 学生对象
	 */
	public Student queryStudentBySage(int sage) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			String sql = "Select * From student where sage = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sage);
			rs = ps.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				
				student = new Student(no, name, age, address);
			}
			
			return student;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}
	
	/**
	 * 查找全部学生
	 * 
	 * @param sno
	 * @return student
	 */
	public List<Student> queryAllStudents() {
		List<Student> students = new ArrayList<Student>();
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "Select * From student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				
				students.add(new Student(no, name, age, address));				
			}
			
			return students;
			
					
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}
	
	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return boolean 执行结果
	 */
	public boolean addStudent(Student student) {
		String sql = "Insert Into student values(?, ?, ?, ?)";
		Object[] params = 
			{student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
		
		return DBUtils.executeUpdate(sql, params);
	}
	
	/**
	 * 根据 sno 删除学生
	 * 
	 * @param student
	 * @return boolean 执行结果
	 */
	public boolean deleteStudentBySno(int sno) {
		String sql = "Delete From student where sno = ?";
		Object[] params = {sno};
		
		return DBUtils.executeUpdate(sql, params);
	}
	
	/**
	 * 根据 sno 修改指定学生
	 * 
	 * @param sno
	 * @return boolean 执行结果
	 */
	public boolean updateStudentBySno(int sno, Student student) {
		String sql = "Update student set sname = ?, sage = ?, saddress= ? where sno = ?";
		Object[] params = {student.getSname(), student.getSage(), student.getSaddress(), sno};
		
		return DBUtils.executeUpdate(sql, params);
	}

	/**
	 * 查询学生表的总数据量
	 * 
	 * @return int 总数据量
	 */
	public int getTotalCount() {
		String sql = "Select Count(1) from student";
		return DBUtils.getTotalCount(sql);
	}
	
	/**
	 * 查询某一页的数据
	 * 
	 * @param currentPage 当前页（页码）
	 * @param pageSize 页面大小（每页显示的数据条数）
	 * @return List<Student> 学生集合
	 */
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		String sql = "Select * From student limit ?, ?";
		Object[] params = {currentPage*pageSize, pageSize};
		
		ResultSet rs =  DBUtils.executeQuery(sql, params);
		List<Student> students = new ArrayList<Student>();
		
		try {
			while (rs.next()) {
				Student student = new Student(
						rs.getInt("sno"), rs.getString("sname"), 
						rs.getInt("sage"), rs.getString("saddress"));
				
				students.add(student);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}
}
