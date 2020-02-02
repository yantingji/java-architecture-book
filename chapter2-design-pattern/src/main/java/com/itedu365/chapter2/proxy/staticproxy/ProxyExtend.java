package com.itedu365.chapter2.proxy.staticproxy;

public class ProxyExtend extends Customer {

	public void buyCar(int cash) {
		// 通过代理实现对购车者的控制，或者提供其他服务，如贷款，保险等
		if (cash < 100000) {
			System.out.println("你的钱(" + cash + ")不够买一辆车!");
			return;
		} else {
			super.setCash(cash);
			super.buyCar();
		}
	}

}
