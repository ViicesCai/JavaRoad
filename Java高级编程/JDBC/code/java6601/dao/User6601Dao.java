/**
 * 
 */
package java6601.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问类
 * 
 * @author CAI
 *
 */
public class User6601Dao {
	
	/**
	 * 增加新的用户信息到数据表中
	 * 
	 * @param user 用户对象
	 */
	public void addUser(User6601 user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil6601.getConnection6601();
			
			// SQL 语句
			String sql = "Insert Into user6601(name, password, telNumber) Values (?, ?, ?)";
			// 预处理 SQL 语句
			ps = conn.prepareStatement(sql);
			
			// 给占位符赋值
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.getTelNumber());
			
			// 执行 SQL 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 关闭资源
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * 更新指定 ID 的用户信息
	 * 
	 * @param id 用户对象对应的id
	 * @param newUser 新的用户对象
	 */
	public void update6601(int id, User6601 newUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil6601.getConnection6601();
			
			// SQL 语句
			String sql = "Update user6601 Set name = ?, password = ?, telNumber = ? Where id = ?";
			// 预处理 SQL 语句
			ps = conn.prepareStatement(sql);
			
			// 给占位符赋值
			ps.setObject(1, newUser.getName());
			ps.setObject(2, newUser.getPassword());
			ps.setObject(3, newUser.getTelNumber());
			ps.setObject(4, id);
			
			// 执行 SQL 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * 删除 id 指定的用户信息
	 * 
	 * @param id 用户对象对应的 id
	 */
	public void delete6601(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil6601.getConnection6601();
			
			// SQL 语句
			String sql = "Delete From user6601 Where id = ?";
			// 预处理 SQL 语句
			ps = conn.prepareStatement(sql);
			
			// 给占位符赋值
			ps.setObject(1, id);
			// 执行 SQL 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 关闭资源
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * 将数据表中的数据转为集合
	 * 
	 * @return 存放数据的集合
	 */
	public List<User6601> findAll6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User6601> list = new ArrayList<User6601>();
		
		try {
			// 建立数据库连接
			conn = DBUtil6601.getConnection6601();
			
			// SQL 语句
			String sql = "Select * From user6601";
			// 预处理 SQL 语句
			ps = conn.prepareStatement(sql);
			
			// 执行 SQL 语句，返回结果集
			rs = ps.executeQuery();
			
			// 遍历
			while (rs.next()) {
				User6601 user = new User6601(rs.getString("name"), rs.getString("password"), rs.getString("telNumber"));
				user.setId(rs.getInt("id"));
				list.add(user);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 关闭资源
			DBUtil6601.closeSource(conn, ps, rs);
		}
		
		return list;
	}
}
