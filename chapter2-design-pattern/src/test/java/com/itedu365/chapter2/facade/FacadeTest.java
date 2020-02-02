package com.itedu365.chapter2.facade;

import org.junit.Test;

public class FacadeTest {

	@Test
	public void test() {
		// 实例化电器类
		Light light = new Light();
		Television television = new Television();
		Aircondition aircondition = new Aircondition();

		Facade facade = new Facade(light, television, aircondition);

		// 客户端直接与外观对象进行交互
		facade.on();
		System.out.println("可以看电视了");
		facade.off();
		System.out.println("可以睡觉了");
	}

}
