package com.itedu.example.dao;

import com.itedu.example.domain.SysUser;

public interface UserDao {
	public SysUser findByUserName(String username);
}
