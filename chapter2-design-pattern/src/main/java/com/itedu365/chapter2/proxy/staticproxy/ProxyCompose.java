package com.itedu365.chapter2.proxy.staticproxy;

public class ProxyCompose implements BuyCar {
	private Customer customer;

	public ProxyCompose() {
	}

	public ProxyCompose(Customer customer) {
		this.customer = customer;
	}

	public void buyCar() {
		// 通过代理实现对购车者的控制，或者提供其他服务，如贷款，保险等
		int cash = customer.getCash();
		if (cash < 100000) {
			System.out.println("你的钱(" + cash + ")不够买一辆车!");
			return;
		}
		customer.buyCar();
	}

}
