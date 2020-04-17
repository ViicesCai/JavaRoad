/**
 * 
 */
package java6601.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * ִ�� SQL ���
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

	// ��
	private static void insert6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = getConnection();
			
			// ִ�� SQL ��䣺��� ѧ�ż�ѧ������
			String sql = "Insert Into xslist(Sno,Sname) Values ('211906601', '��ά��')";
			// Ԥ���� SQL ��� 
			ps = conn.prepareStatement(sql);
			// ִ�� SQl ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
	}
	
	// ��
	private static void update6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = getConnection();
			
			// SQL ��䣺Ϊ ָ���� Sno �޸� lx1��lx2
			String sql = "Update xslist Set lx1 = id*2 , lx2 = lx1+id Where Sno = 211906601";
			// Ԥ���� SQL ��� 
			ps = conn.prepareStatement(sql);
			// ִ�� SQl ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
	}
	
	// ɾ��
	private static void delete6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = getConnection();
			
			// SQL ��䣺ɾ�� lx1 �� 3 �ı������ֶ�
			String sql = "Delete From xslist Where lx1%3 = 0";
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			// ִ�� SQl ���
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
			// �������ݿ�����
			conn = getConnection();
			
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			
			// ���ռλ��
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			
			// ִ�� SQL ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps);
		}
		
	}
	
	/**
	 * �������ݿ�����
	 * 
	 * @return ���Ӷ���
	 */
	private static Connection getConnection() {
		final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ����
		final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tempdb"; // ���ݿ�·��
		final String USER = "sa"; // �û���
		final String PW = "abc123"; // ����
		
		Connection conn = null;
		
		try {
			// ���ز�ע�����ݿ�����
			Class.forName(DRIVER);
			
			// �������ݿ�
			conn = DriverManager.getConnection(URL, USER, PW);
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * �ر���Դ
	 * 
	 * @param conn ���Ӷ���
	 * @param ps PreparedStatement ����
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
