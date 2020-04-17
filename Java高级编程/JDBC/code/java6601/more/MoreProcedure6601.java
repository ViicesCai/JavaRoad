/**
 * 
 */
package java6601.more;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java6601.dao.DBUtil6601;

/**
 * @author CAI
 *
 */
public class MoreProcedure6601 {
	
	public static void main(String[] args) throws SQLException {
		testProdure6601();
	}
	
	private static void testProdure6601() throws SQLException {
		// �������ݿ�
		Connection conn = DBUtil6601.getConnection6601();
		
		// ���ô洢����
		String sql = "{?=call xslistINSERT(?,?,?,?)}";
		CallableStatement callst = conn.prepareCall(sql);
		
		// Ϊ���������ֵ
		callst.setString(2, "88888");
		callst.setString(3, "�洢����");
		callst.setInt(4, 80);
		callst.setInt(5, 90);
		
		// ע��洢���̵ķ���ֵ����
		callst.registerOutParameter(1, java.sql.Types.INTEGER);
		callst.executeUpdate();
		
		// ��ȡ�洢���̵ķ���ֵ
		int id = callst.getInt(1);
		System.out.println("��ӵ��¼�¼ID��" + id);
		
		// �ر���Դ
		callst.close();
		conn.close();
	}
}
