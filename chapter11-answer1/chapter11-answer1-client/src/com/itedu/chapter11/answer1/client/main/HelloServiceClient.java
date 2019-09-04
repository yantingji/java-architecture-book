package com.itedu.chapter11.answer1.client.main;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.itedu.chapter11.answer1.client.HelloService;
import com.itedu.chapter11.answer1.client.HelloService_Service;

public class HelloServiceClient {

	private final QName qName=new QName(
		    "http://com.itedu.hello","HelloService");

		public static URL getWSDLURL(String urlStr){
		   URL url = null;
		   try {
		    url = new URL(urlStr);
		   } catch (MalformedURLException e) {
		    e.printStackTrace();
		   }
		   return url;
		}

		public void process(URL url){
		   HelloService_Service service = new HelloService_Service(url,qName);
		   HelloService port = service.getHelloServicePort();
		   String response = port.sayHello("world");
		   System.out.println("result:"+response);
		}

		/**
		* @param args
		*/
		public static void main(String[] args) {
		   URL url = getWSDLURL("http://localhost:8080/services/HelloService?wsdl");
		   HelloServiceClient client = new HelloServiceClient();
		   client.process(url);
		}

		}
