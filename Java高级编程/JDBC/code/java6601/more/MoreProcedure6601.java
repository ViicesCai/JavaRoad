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
		// 连接数据库
		Connection conn = DBUtil6601.getConnection6601();
		
		// 调用存储过程
		String sql = "{?=call xslistINSERT(?,?,?,?)}";
		CallableStatement callst = conn.prepareCall(sql);
		
		// 为输入参数赋值
		callst.setString(2, "88888");
		callst.setString(3, "存储过程");
		callst.setInt(4, 80);
		callst.setInt(5, 90);
		
		// 注册存储过程的返回值类型
		callst.registerOutParameter(1, java.sql.Types.INTEGER);
		callst.executeUpdate();
		
		// 读取存储过程的返回值
		int id = callst.getInt(1);
		System.out.println("添加的新纪录ID：" + id);
		
		// 关闭资源
		callst.close();
		conn.close();
	}
}
