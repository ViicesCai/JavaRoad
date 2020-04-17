	/**
 * 
 */
package java6601.lesson12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class Select6601 {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("������Ҫ���ҵ������а������κ���Ϣ��");
		String keyWord = "%" + in.next().trim() + "%";
		
		System.out.print("��������ҵ� id ��Χ��");
		int scope = in.nextInt();
		
		find6601(keyWord, scope);
		
		in.close();
	}

	public static void find6601(String rule, int sum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "Select ID,Sno,Sname From xslist Where Sname Like ? And ID < ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rule);
			ps.setInt(2, sum);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String sno = rs.getString("Sno");
				String sname = rs.getString("Sname");
				
				System.out.printf("%-4d,%9s,%-10s\n", rs.getInt(1), sno, sname);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeSource(conn, ps, rs);
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
	private static void closeSource(Connection conn, PreparedStatement ps, ResultSet rs) {
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
