package com.itedu365.chapter2.template;

public class ManEat extends AbstractTemplate {

	@Override
	public void orderFood() {
		System.out.println("点了很多肉菜与酒");
	}

	@Override
	public void pay() {
		System.out.println("直接刷卡，我很酷");
	}

}
