/**
 * 
 */
package edu.fdzc.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * boolean 与 int 转换器 
 * 
 * @author CAI
 *
 */
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean> {

	/**
	 * java 类型 转 数据库类型
	 * 
	 * ps : PreparedStatement对象
	 * i : PreparedStatement对象操作参数的位置
	 * parameter : java 值
	 * JdbcType : 数据库类型
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		
		if (parameter) { // 如果 java 值为 true
			ps.setInt(i, 1); // 将该变量值变为 1
			
		} else {
			ps.setInt(i, 0);
		}
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// 根据列名取值
		int sex = rs.getInt(columnName);
		
		return sex == 1 ? true:false;
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// 根据列下标
		int sex = rs.getInt(columnIndex);
				
		return sex == 1 ? true:false;
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sex = cs.getInt(columnIndex);
		return sex == 1 ? true:false;
	}
	
}
