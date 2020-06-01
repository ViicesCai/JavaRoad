package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
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
	public static void closeConnection(Connection conn, Statement stmt) {
		try {
			// 防止空指针异常
			if (stmt != null) {
				stmt.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param conn 连接对象
	 * @param rs 数据集对象
	 */
	public static void closeConnection(Connection conn, ResultSet rs) {
		try {
			// 防止空指针异常
			if (rs != null) {
				rs.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
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
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			// 防止空指针异常
			if (rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据SQL语句和参数 创建预编译语句
	 * 
	 * @param sql 数据库语句
	 * @param params 参数数组
	 * @return preparedStatement
	 * @throws SQLException
	 */
	private static PreparedStatement createPreparedStatement
	(String sql, Object[] params) throws SQLException {
		conn = getConnection();
		ps = conn.prepareStatement(sql);
			
		if (ps != null) { // 防止空指针异常
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
		}
				
		return ps;
	}
	
	/**
	 * 通用的增删改操作
	 * 
	 * @param sql 数据库语句
	 * @param params 参数数组
	 * @return boolean 执行结果
	 */
	public static boolean executeUpdate(String sql, Object[] params) {		
		try {
			ps = createPreparedStatement(sql, params);
			
			// 执行
			int count = ps.executeUpdate();
			
			// 判断影响行数，0行表示 执行失败
			if (count > 0) {
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		} finally {
			closeConnection(conn, ps);
		}
	}
	
	/**
	 * 通用的查询操作
	 * 
	 * @param sql 数据库语句
	 * @param params 参数数组
	 * @return ResultSet 结果集
	 */
	public static ResultSet executeQuery(String sql, Object[] params) {	
		
		try {
			return createPreparedStatement(sql, params).executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 返回查询总数
	 * 
	 * @param sql 数据库语句
	 * @return int 总数据量
	 */
	public static int getTotalCount(String sql) {
		int count = -1; // 总数据量
		
		try {
			ps = createPreparedStatement(sql, null);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DBUtils.closeConnection(getConnection(), ps, rs);
		}
		
		return count;
	}
}
