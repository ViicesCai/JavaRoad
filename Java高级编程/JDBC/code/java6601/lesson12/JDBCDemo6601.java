/**
 * 
 */
package java6601.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�����
 * 
 * @author CAI
 *
 */
public class JDBCDemo6601 {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ����
	private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tempdb"; // ���ݿ�·��
	private static final String USER = "sa"; // �û���
	private static final String PW = "abc123"; // ����
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// ����ע������
		Class.forName(DRIVER);
		
		// �������ݿ�
		Connection conn = DriverManager.getConnection(URL, USER, PW);
		
		System.out.println("���ݿ�����" + conn.getMetaData().getDatabaseProductName()); // ������ݿ���
		System.out.println("���ݿ�汾�ţ�" + conn.getMetaData().getDatabaseProductVersion()); // ������ݿ�汾��
		System.out.println("���ݿ�URL��" + conn.getMetaData().getURL()); // ������ݿ� URL
		
		// �ر�����
		conn.close();
	}
}
