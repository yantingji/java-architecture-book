package com.itedu365.chapter2.responsibility;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeveloperTest {

	@Test
	public void test() {
		// 组装责任链
		Developer developer1 = new Programmar();
		Developer developer2 = new SystemEngineer();
		Developer developer3 = new Architector();
		developer1.setNext(developer2);
		developer2.setNext(developer3);
		// 提交请求
		developer1.handleRequest(8);
	}

}
