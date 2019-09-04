package com.itedu.chapter11.answer5.server;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface MyWebServiceI {
 
	String sayHi(@WebParam(name = "text") String text);
}