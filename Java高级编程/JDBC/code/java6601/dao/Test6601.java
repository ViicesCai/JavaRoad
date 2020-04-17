/**
 * 
 */
package java6601.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ���ݿ����Ӳ�����
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
			// ������ݿ�汾��
			System.out.println("���ݿ�汾��" + conn.getMetaData().getDatabaseProductVersion());
			
			// �ر����ݿ�
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
		newUser.setName("�ұ��޸���");
		newUser.setPassword("123dao");
		newUser.setTelNumber("12222222222");
		
		new User6601Dao().update6601(1, newUser);
	}
	
	private static void testDelete6601() {
		new User6601Dao().delete6601(5);
	}
}
