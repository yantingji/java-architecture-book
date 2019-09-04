package com.itedu.answer4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itedu.answer4.db.DBHelper;
import com.itedu.answer4.dto.UserDto;

public class LoginDao {
	
	public UserDto getUser(String username) throws SQLException {
		Connection conn = DBHelper.getConnection();
		PreparedStatement stmt = conn
				.prepareStatement("select password ,id from t_user where trim(username)=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		UserDto user = new UserDto();
		while (rs.next()) {
			user.setPassword(rs.getString(1));
		}
		DBHelper.close(conn, stmt, rs);
		return user;
	}
		
}

