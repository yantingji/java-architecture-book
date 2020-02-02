package com.itedu365.chapter2.template;

public abstract class AbstractTemplate {
	// 相同内容：一人一个座位
	public void getSeat() {
		System.out.println("找座位");
	}

	// 不同内容：每个客户点菜内容不一样
	public abstract void orderFood();

	// 相同内容：上菜之后，就是吃
	public void getService() {
		System.out.println("吃饭");
	}

	// 不同内容：付款方式不一样
	public abstract void pay();

	final void getEatService() {
		this.getSeat();
		this.orderFood();
		this.getService();
		this.pay();
	}
}
