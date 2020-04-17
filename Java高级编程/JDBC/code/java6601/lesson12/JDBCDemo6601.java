/**
 * 
 */
package java6601.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 * 
 * @author CAI
 *
 */
public class JDBCDemo6601 {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 类名
	private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tempdb"; // 数据库路径
	private static final String USER = "sa"; // 用户名
	private static final String PW = "abc123"; // 密码
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 加载注册驱动
		Class.forName(DRIVER);
		
		// 连接数据库
		Connection conn = DriverManager.getConnection(URL, USER, PW);
		
		System.out.println("数据库名：" + conn.getMetaData().getDatabaseProductName()); // 输出数据库名
		System.out.println("数据库版本号：" + conn.getMetaData().getDatabaseProductVersion()); // 输出数据库版本号
		System.out.println("数据库URL：" + conn.getMetaData().getURL()); // 输出数据库 URL
		
		// 关闭连接
		conn.close();
	}
}
