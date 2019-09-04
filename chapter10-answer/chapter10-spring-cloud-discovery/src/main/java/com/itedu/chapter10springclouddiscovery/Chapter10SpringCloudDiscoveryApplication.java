package com.itedu.chapter10springclouddiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Chapter10SpringCloudDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter10SpringCloudDiscoveryApplication.class, args);
	}

}
