/**
 * 
 */
package java6601.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接测试类
 * 
 * @author CAI
 *
 */
public class Test6601 {

	public static void main(String[] args) {
		testDelete6601();
		testFindAll6601();
	}

	private static void testDBUtil6601() {
		Connection conn = DBUtil6601.getConnection6601();

		try {
			// 输出数据库版本号
			System.out.println("数据库版本号" + conn.getMetaData().getDatabaseProductVersion());
			
			// 关闭数据库
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testAdd6601() {
		User6601 user = new User6601();
		user.setName("Hacker");
		user.setPassword("ssiopzcczx");
		user.setTelNumber("31232sadasd");
		
		new User6601Dao().addUser(user);
	}
	
	private static void testFindAll6601() {
		for (User6601 user : new User6601Dao().findAll6601()) {
			System.out.println(user);
		}
	}
	
	private static void testUpdate6601() {
		User6601 newUser = new User6601();
		newUser.setName("我被修改了");
		newUser.setPassword("123dao");
		newUser.setTelNumber("12222222222");
		
		new User6601Dao().update6601(1, newUser);
	}
	
	private static void testDelete6601() {
		new User6601Dao().delete6601(5);
	}
}
