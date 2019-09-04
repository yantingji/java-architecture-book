package com.itedu.chapter10springcloudprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaClient
@RestController
public class Chapter10SpringCloudProviderApplication {
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name){
	System.out.println("Welcome： "+name);
	return "Welcome："+name;
	}

	public static void main(String[] args) {
		SpringApplication.run(Chapter10SpringCloudProviderApplication.class, args);
	}

}
