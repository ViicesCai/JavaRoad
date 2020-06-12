/**
 * 
 */
package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * 
 * @author CAI
 *
 */
public class DBUtils {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
	private static final String USER = "root";
	private static final String PW = "abc123";
	
	/**
	 * 注册驱动
	 */
	static {
		
		try {
			// 加载并注册数据库驱动
			Class.forName(DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return connection 对象
	 */
	public static Connection getConnection() {
		
		try {
			// 与数据库建立连接
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			
			// 返回连接对象
			return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param conn 连接对象
	 * @param ps 执行对象
	 */
	public static void closeConnection(Connection conn, PreparedStatement ps) {
		try {
			// 防止空指针异常
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param conn 连接对象
	 * @param ps 执行对象
	 * @param rs 数据集对象
	 */
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			// 防止空指针异常
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
