package com.itedu.mapper;

import com.itedu.dto.UserDto;

public interface UserMapper {
	//通过用户名和密码查询User
    UserDto selectUserByUsernameAndPassword(UserDto user);

}
