/**
 * 
 */
package java6601.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接的工具类
 * 
 * @author CAI
 *
 */
public class DBUtil6601 {
	private static Properties prop;

	/**
	 * 读取配置信息，注册驱动
	 */
	static {
		prop = new Properties();
		
		try {
			// 从配置文件中加载配置项：使用Properties的load()方法
			prop.load(new FileInputStream("config\\database6601.proerties"));
			
			// 读取配置项中driver的结果：使用Properties的getProperty()方法
			String driver = prop.getProperty("driver");
			
			// 加载并注册数据库驱动
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库
	 * 
	 * @return 数据库连接
	 */
	public static Connection getConnection6601() {
		// 读取配置项中url、user、pw的结果:使用Properties的getProperty()方法
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pw = prop.getProperty("pw");
		
		try {
			// 与数据库建立连接
			Connection conn = DriverManager.getConnection(url, user, pw);
			
			// 返回Connection对象
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
	public static void closeSource(Connection conn, PreparedStatement ps) {
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
	
	/**
	 * 关闭资源
	 * 
	 * @param conn 连接对象
	 * @param ps 执行对象
	 * @param rs 数据集对象
	 */
	public static void closeSource(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
