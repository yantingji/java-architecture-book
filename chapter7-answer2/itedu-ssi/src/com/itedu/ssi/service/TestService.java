package com.itedu.ssi.service;

import java.util.List;

import com.itedu.ssi.model.UserModel;

public interface TestService {
	public void service() throws Exception;

	public UserModel selectByid(String id);

	public List<UserModel> queryforlist();
}
