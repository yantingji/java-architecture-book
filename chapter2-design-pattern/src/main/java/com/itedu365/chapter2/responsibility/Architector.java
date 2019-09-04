package com.itedu365.chapter2.responsibility;

public class Architector extends Developer {

	@Override
	public void handleRequest(int difficuty) {
		if (difficuty <= 10) {
			System.out.println("架构师，能处理这个难度级别的问题：" + difficuty);
		} else {
			if (getNext() != null) {
				getNext().handleRequest(difficuty);
			} else {
				System.out.println("这个问题太难，项目组内没法解决！");
			}
		}
	}
}
