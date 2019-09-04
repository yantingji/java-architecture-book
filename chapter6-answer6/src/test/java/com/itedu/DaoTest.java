package com.itedu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itedu.dto.UserDto;
import com.itedu.mapper.UserMapper;

public class DaoTest {
	ApplicationContext context = null;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
	}
	
	@Test
	public void testSelectByPrimaryKey(){
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		UserDto user=new UserDto();
		user.setName("yan");
		user.setPassword("yan");
		UserDto user1 = userMapper.selectUserByUsernameAndPassword(user);
		System.out.println(user1.getPassword());
	}
}
