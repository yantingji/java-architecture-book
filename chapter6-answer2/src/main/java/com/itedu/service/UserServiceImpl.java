package com.itedu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itedu.dto.User;
import com.itedu.mapper.UserMapper;

public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper; // 通过@Autowired向spring容器注入UserMapper

	// 通过用户名和密码查询User
	@Override
	public User selectUserByUsernameAndPassword(User user) {
		User u = userMapper.selectUserByUsernameAndPassword(user);
		return u;
	}

}
