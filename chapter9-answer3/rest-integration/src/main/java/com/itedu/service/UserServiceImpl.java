package com.itedu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itedu.dto.UserDto;
import com.itedu.exception.SystemException;
import com.itedu.mapper.UserMapper;

public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper; // 通过@Autowired向spring容器注入UserMapper

	// 通过用户名和密码查询User
	@Override
	public UserDto selectUserByUsernameAndPassword(UserDto user) {
		UserDto resultUser = userMapper.selectUserByUsernameAndPassword(user);
		
//		// 用户不存在
//		if(resultUser==null) {
//			throw new SystemException("E0001");
//		}
//		
//		// 用户密码不正常
//		if(!resultUser.getPassword().equals(user.getPassword())) {
//			// 用户登录此时更新
//			// TODO
//		    throw new SystemException("E0002");
//		}
//		
//		// 登录错误次数超限
//		if(resultUser.getLoginWrongTimes()>6) {
//		    throw new SystemException("E0003");
//		}
//		
//		// 密码有效期限
//		if(resultUser.getLoginWrongTimes()>6) {
//		    throw new SystemException("E0004");
//		}
		
		return resultUser;
	}

}
