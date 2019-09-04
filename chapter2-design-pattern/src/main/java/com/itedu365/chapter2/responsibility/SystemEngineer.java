package com.itedu365.chapter2.responsibility;

public class SystemEngineer extends Developer {

	@Override
	public void handleRequest(int difficuty) {
		if (difficuty < 8) {
			System.out.println("工程师，能处理这个难度级别的问题：" + difficuty);
		} else {
			if (getNext() != null) {
				getNext().handleRequest(difficuty);
			} else {
				System.out.println("这个问题太难，项目组内没法解决！");
			}
		}
	}
}
