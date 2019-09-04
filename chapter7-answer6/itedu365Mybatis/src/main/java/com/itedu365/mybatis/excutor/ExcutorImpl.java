package com.itedu365.mybatis.excutor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.itedu365.mybatis.jdbc.DBHelper;

public class ExcutorImpl implements Excutor {

	@Override
	public <T> Object query(String sqlId, Object parameter) {
		Connection connection = DBHelper.getConnection();
		ResultSet rs = null;
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(SqlMapParser.getSqlContent(sqlId));
			pre.setString(1, parameter.toString());
			rs = pre.executeQuery();
			String[] columnNames = getNames(rs);
			Object object = null;
			String resultClass = SqlMapParser.getSqlResultClass(sqlId);
			Class<?> clazz = Class.forName(resultClass);

			if (rs.next()) {
				object = clazz.newInstance();
				for (int i = 0; i < columnNames.length; i++) {
					String columnName = columnNames[i];
					String methodName = "set" + columnName.substring(0, 1).toUpperCase()
							+ columnName.substring(1, columnName.length());
					Object value = rs.getObject(columnName);
					if (value != null) {
						Method method = clazz.getMethod(methodName, value.getClass());
						if (method != null) {
							method.invoke(object, value);
						}
					}

				}
			}
			return object;
		} catch (NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException
				| IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	private static String[] getNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String[] names = new String[count];
		for (int i = 0; i < count; i++) {
			names[i] = rsmd.getColumnLabel(i + 1);
		}
		return names;
	}
}
