package com.itedu.chapter10springcloudconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ConsumerController {
	@Value("${user.userServerPath}")
	private String url;
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("/user/{name}")
	public String getUserById(@PathVariable String name){
	return this.restTemplate.getForObject(this.url+name, String.class);
	}

}
