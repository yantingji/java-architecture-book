package com.itedu365.chapter2.template;

public class WomanEat extends AbstractTemplate {

	@Override
	public void orderFood() {
		System.out.println("点了很多蔬菜");
	}

	@Override
	public void pay() {
		System.out.println("我用微信");
	}

}
