package com.itedu.mapper;

import com.itedu.dto.User;

public interface UserMapper {
	// 通过用户名和密码查询User
	User selectUserByUsernameAndPassword(User user);

}
