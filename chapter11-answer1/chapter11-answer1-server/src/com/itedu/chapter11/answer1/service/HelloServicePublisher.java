package com.itedu.chapter11.answer1.service;

import javax.xml.ws.Endpoint;

class HelloServicePublisher {

	public void publish() {
		Endpoint.publish("http://localhost:8080/services/HelloService", new HelloService());
	}

}
