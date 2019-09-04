package com.itedu.chapter11.answer5.server.main;

import javax.xml.ws.Endpoint;

import com.itedu.chapter11.answer5.server.MyWebServiceImpl;

public class WebServiceServerMain {
	public static void main(String[] args) {
		System.out.println("web service start...");
		MyWebServiceImpl myWebService = new MyWebServiceImpl();
		String address = "http://localhost:8080/myWebService";
		Endpoint.publish(address, myWebService);
		System.out.println("web service started"); 
	}
}
