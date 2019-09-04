package com.itedu.service;

import com.itedu.dto.UserDto;

public interface IUserService {
	// 通过用户名和密码查询User
	public UserDto selectUserByUsernameAndPassword(UserDto user);

}
