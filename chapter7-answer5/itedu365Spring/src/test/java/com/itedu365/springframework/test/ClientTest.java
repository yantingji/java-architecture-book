package com.itedu365.springframework.test;

import org.junit.Test;

import com.itedu365.springframework.context.support.ClassPathXMLApplicationContext;
import com.itedu365.springframework.test.service.StudentService;

public class ClientTest {
	@Test
	public void test() {
		 ClassPathXMLApplicationContext cts = new ClassPathXMLApplicationContext("bean.xml");
	        StudentService ss = (StudentService) cts.getBean("studentService");
	        System.out.println(ss.getClass().getName());
	        ss.save();
	}



}
