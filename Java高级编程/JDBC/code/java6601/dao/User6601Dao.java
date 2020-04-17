/**
 * 
 */
package java6601.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ݷ�����
 * 
 * @author CAI
 *
 */
public class User6601Dao {
	
	/**
	 * �����µ��û���Ϣ�����ݱ���
	 * 
	 * @param user �û�����
	 */
	public void addUser(User6601 user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil6601.getConnection6601();
			
			// SQL ���
			String sql = "Insert Into user6601(name, password, telNumber) Values (?, ?, ?)";
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			
			// ��ռλ����ֵ
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.getTelNumber());
			
			// ִ�� SQL ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// �ر���Դ
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * ����ָ�� ID ���û���Ϣ
	 * 
	 * @param id �û������Ӧ��id
	 * @param newUser �µ��û�����
	 */
	public void update6601(int id, User6601 newUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil6601.getConnection6601();
			
			// SQL ���
			String sql = "Update user6601 Set name = ?, password = ?, telNumber = ? Where id = ?";
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			
			// ��ռλ����ֵ
			ps.setObject(1, newUser.getName());
			ps.setObject(2, newUser.getPassword());
			ps.setObject(3, newUser.getTelNumber());
			ps.setObject(4, id);
			
			// ִ�� SQL ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * ɾ�� id ָ�����û���Ϣ
	 * 
	 * @param id �û������Ӧ�� id
	 */
	public void delete6601(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil6601.getConnection6601();
			
			// SQL ���
			String sql = "Delete From user6601 Where id = ?";
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			
			// ��ռλ����ֵ
			ps.setObject(1, id);
			// ִ�� SQL ���
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// �ر���Դ
			DBUtil6601.closeSource(conn, ps);
		}
	}
	
	/**
	 * �����ݱ��е�����תΪ����
	 * 
	 * @return ������ݵļ���
	 */
	public List<User6601> findAll6601() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User6601> list = new ArrayList<User6601>();
		
		try {
			// �������ݿ�����
			conn = DBUtil6601.getConnection6601();
			
			// SQL ���
			String sql = "Select * From user6601";
			// Ԥ���� SQL ���
			ps = conn.prepareStatement(sql);
			
			// ִ�� SQL ��䣬���ؽ����
			rs = ps.executeQuery();
			
			// ����
			while (rs.next()) {
				User6601 user = new User6601(rs.getString("name"), rs.getString("password"), rs.getString("telNumber"));
				user.setId(rs.getInt("id"));
				list.add(user);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// �ر���Դ
			DBUtil6601.closeSource(conn, ps, rs);
		}
		
		return list;
	}
}
