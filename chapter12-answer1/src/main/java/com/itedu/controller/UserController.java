package com.itedu.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itedu.bean.User;

@RestController
public class UserController {
	
	@ResponseBody
	@RequestMapping("/user")
	public User getUser(){
		User user = new User();
		
		user.setAge(30);
		user.setBirthday(LocalDate.now());
		user.setName("itedu");
		
		return user;
	}

}