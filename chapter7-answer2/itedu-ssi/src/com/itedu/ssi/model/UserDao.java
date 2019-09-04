package com.itedu.ssi.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserDao extends SqlMapClientDaoSupport {
	public Object insertObject(UserModel userModel) throws SQLException {
		return getSqlMapClientTemplate().insert("insertPerson", userModel);
	}

	public UserModel selectbyid(String id) {
		return (UserModel) getSqlMapClientTemplate().queryForObject("selectByid", id);

	}

	public List<UserModel> queryforlist() {
		return getSqlMapClientTemplate().queryForList("userModel.listUserModel");

	}

}
