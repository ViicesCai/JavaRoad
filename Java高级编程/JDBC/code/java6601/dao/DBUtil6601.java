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
 * ���ݿ����ӵĹ�����
 * 
 * @author CAI
 *
 */
public class DBUtil6601 {
	private static Properties prop;

	/**
	 * ��ȡ������Ϣ��ע������
	 */
	static {
		prop = new Properties();
		
		try {
			// �������ļ��м��������ʹ��Properties��load()����
			prop.load(new FileInputStream("config\\database6601.proerties"));
			
			// ��ȡ��������driver�Ľ����ʹ��Properties��getProperty()����
			String driver = prop.getProperty("driver");
			
			// ���ز�ע�����ݿ�����
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�
	 * 
	 * @return ���ݿ�����
	 */
	public static Connection getConnection6601() {
		// ��ȡ��������url��user��pw�Ľ��:ʹ��Properties��getProperty()����
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pw = prop.getProperty("pw");
		
		try {
			// �����ݿ⽨������
			Connection conn = DriverManager.getConnection(url, user, pw);
			
			// ����Connection����
			return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * �ر���Դ
	 * 
	 * @param conn ���Ӷ���
	 * @param ps ִ�ж���
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
	 * �ر���Դ
	 * 
	 * @param conn ���Ӷ���
	 * @param ps ִ�ж���
	 * @param rs ���ݼ�����
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
