package com.itedu365.chapter2.template;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractTemplateTest {

	@Test
	public void test() {
		ManEat manEat = new ManEat();
		manEat.getEatService();
		WomanEat womanEat = new WomanEat();
		womanEat.getEatService();
	}

}
