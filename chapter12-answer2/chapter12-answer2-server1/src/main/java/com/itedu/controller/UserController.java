package com.itedu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itedu.bean.User;

@RestController
public class UserController {
	
	@ResponseBody
	@RequestMapping("/user")
	public User getUser(){
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
		
		User user = new User();
		RestTemplate restTemplate= new RestTemplate(factory);
		
		String resourceUrl = "http://localhost:8080/chapter12-answer2-server2/username";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl, String.class);
		user.setName(responseEntity.getBody());
		user.setAge(18);
		
		return user;
	}
}