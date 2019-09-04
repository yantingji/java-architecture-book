package com.itedu.chapter11.answer1.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "HelloService", portName = "HelloServicePort", targetNamespace = "http://com.itedu.hello")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class HelloService {
	@WebMethod
	public String sayHello(String s) {
		System.out.println("hello," + s);
		return s+"server";
	}
}
