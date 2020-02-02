package com.itedu365.chapter2.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyTest {

	@Test
	public void test() {
		ContextSalesMan salesMan;

		// 春节来了，使用春节促销活动
		System.out.println("对于春节：");
		salesMan = new ContextSalesMan("spring");
		salesMan.SalesManShow();

		// 中秋节来了，使用中秋节促销活动
		System.out.println("对于中秋节：");
		salesMan = new ContextSalesMan("fall");
		salesMan.SalesManShow();

	}

}
