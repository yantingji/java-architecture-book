package com.itedu.chapter11.answer5.server;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.itedu.chapter11.answer5.server.MyWebServiceI", serviceName = "Hello")
public class MyWebServiceImpl implements MyWebServiceI {
 
	public String sayHi(@WebParam(name = "text") String text) {
		System.out.println("Server Info: " + text);
		return "Hello, from server: " + text;
	}
}
