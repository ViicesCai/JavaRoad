/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Login;

/**
 * 登录模型：用于处理登录操作（查询数据库）
 * 
 * @author CAI
 *
 */
public class LoginDao {
	/**
	 * 登录操作
	 */
	public static int login(Login login) {
		Connection conn = DBUtils.getConnection();
		String sql = "select count(*) from login where name = ? and pwd = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = -1;
		// 登录标志：-1:系统异常，0：用户名或密码有误，1：登录成功
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getName());
			pstmt.setString(2, login.getPwd());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (result > 0) { // 登录成功
				return 1;
				
			} else { // 登录失败：用户名或密码有误
				return 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			
		} finally {
			DBUtils.closeConnection(conn, pstmt, rs);
		}
	}
}
