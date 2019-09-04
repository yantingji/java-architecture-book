package com.itedu.ssi.service;

import java.util.List;

import com.itedu.ssi.model.UserDao;
import com.itedu.ssi.model.UserModel;

public class TestServiceImpl implements TestService {

	private UserDao userDao = null;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void service() throws Exception {
		UserModel personInsert = new UserModel();
		personInsert.setId("001");
		personInsert.setUsername("itedu");
		userDao.insertObject(personInsert);

		System.out.println("this is service function");

	}

	public UserModel selectByid(String id) {

		return userDao.selectbyid(id);
	}

	public List<UserModel> queryforlist() {
		return userDao.queryforlist();
	}

}
