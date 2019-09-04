package com.itedu.chapter11.answer5.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.itedu.chapter11.answer5.server.MyWebServiceI;

public class WebServiceClientMain {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
		jwpfb.setServiceClass(MyWebServiceI.class);
		jwpfb.setAddress("http://localhost:8080/myWebService");
		MyWebServiceI myWebService = (MyWebServiceI) jwpfb.create();
		System.out.println(myWebService.sayHi("world!"));
 
	}
}
