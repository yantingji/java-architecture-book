package com.itedu.aspect.controller;

import org.springframework.stereotype.Controller;

import com.itedu.aspect.log.User;
import com.itedu.aspect.service.UserService;

import javax.inject.Inject;

@Controller
public class UserController {
	//@Autowired
	@Inject
	UserService userService ;
	
	public void addUser(User user) {
		userService.addUser(user);
	}
	
}
