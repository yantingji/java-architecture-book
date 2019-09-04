package com.itedu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itedu.dto.User;
import com.itedu.exception.SystemException;
import com.itedu.mapper.UserMapper;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper; // 通过@Autowired向spring容器注入UserMapper

	// 通过用户名和密码查询User
	@Override
	public User selectUserByUsernameAndPassword(User user) {
		User resultUser = userMapper.selectUserByUsernameAndPassword(user);
		
		// 用户不存在
		if(resultUser==null) {
			throw new SystemException("E0001");
		}
		
		// 用户密码不正常
		if(!resultUser.getPassword().equals(user.getPassword())) {
			// 用户登录错误次数更新
			// TODO
		    throw new SystemException("E0002");
		}
		
		// 登录错误次数超限
		if(resultUser.getLoginWrongTimes()>6) {
		    throw new SystemException("E0003");
		}
		
		// 阻塞中
		if("1".equals(resultUser.getBlockStatus())) {
			throw new SystemException("E0004");
		}
		
		// 临时停止
		if("1".equals(resultUser.getStopStatus())) {
			throw new SystemException("E0005");
		}
		
		return resultUser;
	}

}
