package com.itedu365.chapter2.responsibility;

public abstract class Developer {
	private Developer next;

	public void setNext(Developer next) {
		this.next = next;
	}

	public Developer getNext() {
		return next;
	}

	// 处理请求的方法
	public abstract void handleRequest(int difficuty);
}
