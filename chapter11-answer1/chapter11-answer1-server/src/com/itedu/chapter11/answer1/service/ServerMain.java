package com.itedu.chapter11.answer1.service;

public class ServerMain {
	public static void main(String[] args) {
		HelloServicePublisher publish = new HelloServicePublisher();
		publish.publish();
	}
}
