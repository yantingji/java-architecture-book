package com.itedu365.chapter2.singleton;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itedu365.chapter2.singleton.Singleton;

public class SingletonTest {

	@Test
	public void test() {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton);
	}

}
