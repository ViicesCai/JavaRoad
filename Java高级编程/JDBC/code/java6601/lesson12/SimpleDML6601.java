/**
 * 
 */
package java6601.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 执行 SQL 语句
 * 
 * @author CAI
 *
 */
public class SimpleDML6601 {
	
	public static void main(String[] args) {
//		insert6601();
//		update6601();
//		delete6601();
		String sql = "Delete From xslist";
		excute6601(sql);
	}

	// 增
	private static void insert6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = getConnection();
			
			// 执行 SQL 语句：添加 学号及学生姓名
			String sql = "Insert Into xslist(Sno,Sname) Values ('211906601', '蔡维恒')";
			// 预编译 SQL 语句 
			ps = conn.prepareStatement(sql);
			// 执行 SQl 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
	}
	
	// 改
	private static void update6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = getConnection();
			
			// SQL 语句：为 指定的 Sno 修改 lx1，lx2
			String sql = "Update xslist Set lx1 = id*2 , lx2 = lx1+id Where Sno = 211906601";
			// 预编译 SQL 语句 
			ps = conn.prepareStatement(sql);
			// 执行 SQl 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
	}
	
	// 删除
	private static void delete6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = getConnection();
			
			// SQL 语句：删除 lx1 是 3 的倍数的字段
			String sql = "Delete From xslist Where lx1%3 = 0";
			// 预编译 SQL 语句
			ps = conn.prepareStatement(sql);
			// 执行 SQl 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
	}
	
	private static void excute6601(String sql, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 建立数据库连接
			conn = getConnection();
			
			// 预编译 SQL 语句
			ps = conn.prepareStatement(sql);
			
			// 填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			
			// 执行 SQL 语句
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
		
	}
	
	/**
	 * 建立数据库连接
	 * 
	 * @return 连接对象
	 */
	private static Connection getConnection() {
		final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 类名
		final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tempdb"; // 数据库路径
		final String USER = "sa"; // 用户名
		final String PW = "abc123"; // 密码
		
		Connection conn = null;
		
		try {
			// 加载并注册数据库驱动
			Class.forName(DRIVER);
			
			// 连接数据库
			conn = DriverManager.getConnection(URL, USER, PW);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param conn 连接对象
	 * @param ps PreparedStatement 对象
	 */
	private static void closeSource(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
