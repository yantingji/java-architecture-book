package com.itedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itedu.dto.UserDto;
import com.itedu.form.UserForm;
import com.itedu.integration.bean.Customer;
import com.itedu.integration.gateway.CustomerGateway;
import com.itedu.service.IUserService;

@RestController
public class UserController {
	@Autowired
	public IUserService userService;
	
	@Autowired
	private CustomerGateway customerGateway;
	
	@ResponseBody
	@RequestMapping("/user")
	public UserForm getUser(){
		UserForm user = new UserForm();
		System.out.println("Get Customer info:");
        Customer customer = customerGateway.getCustomerInfo("1");
        System.out.println("Customer id information:" +customer.getId());
        user.setId(customer.getId());
        UserDto userDto = new UserDto();
        userDto.setId(1);
		UserDto userResult = userService.selectUserByUsernameAndPassword(userDto);
		if (userResult != null) {
			user.setUsername(userResult.getUsername());
		} else {
			user.setUsername("yantingji");
		}
		return user;
	}

}