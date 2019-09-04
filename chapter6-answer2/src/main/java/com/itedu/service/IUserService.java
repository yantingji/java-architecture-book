package com.itedu.service;

import com.itedu.dto.User;

public interface IUserService {
	// 通过用户名和密码查询User
	public User selectUserByUsernameAndPassword(User user);

}
