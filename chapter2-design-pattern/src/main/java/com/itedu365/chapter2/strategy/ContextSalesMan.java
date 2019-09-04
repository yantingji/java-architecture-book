package com.itedu365.chapter2.strategy;

public class ContextSalesMan {
	private Strategy strategy;

	public ContextSalesMan() {
	}

	public ContextSalesMan(String festival) {
		switch (festival) {
		// 春节就使用春节促销活动
		case "A":
			strategy = new StrategyA();
			break;
		// 中秋节就使用中秋节促销活动
		case "B":
			strategy = new StrategyB();
			break;
		}

	}

	// 向客户展示促销活动
	public void SalesManShow() {
		strategy.show();
	}
}
