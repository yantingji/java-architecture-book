package com.itedu365.chapter2.proxy.staticproxy;

public class Customer implements BuyCar {

	private int cash;

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public void buyCar() {
		System.out.println("买一辆车花费了-->" + cash + "元");
	}

}
