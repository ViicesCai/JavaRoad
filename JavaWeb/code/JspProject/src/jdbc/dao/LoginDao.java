/**
 * 
 */
package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.entity.Login;

/**
 * 登录类
 * 
 * @author CAI
 *
 */
public class LoginDao {

	public static int login(Login login) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "Select count(*) From login where name = ? and pwd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, login.getName());
			ps.setString(2, login.getPwd());

			rs = ps.executeQuery();
			// 4. 处理结果
			int count = 0;

			if (rs.next()) {
				count = rs.getInt(1);

			}

			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		
		return -1;
		
	}
}
