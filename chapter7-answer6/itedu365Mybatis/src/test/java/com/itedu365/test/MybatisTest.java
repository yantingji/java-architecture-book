package com.itedu365.test;

import org.junit.Test;

import com.itedu365.mybatis.sqlsession.SessionFactory;
import com.itedu365.mybatis.sqlsession.SqlSession;
import com.itedu365.test.entity.User;
import com.itedu365.test.mapper.UserMapper;

public class MybatisTest {

	@Test
	public void test() {
		SqlSession sqlSession = SessionFactory.getSessionInstance();
		UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
	
		User user = userMapper.getUserById("1");
		System.out.println(user.getUsername());
	}

}
