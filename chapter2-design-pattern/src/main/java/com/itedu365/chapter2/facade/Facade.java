package com.itedu365.chapter2.facade;

public class Facade {
	private Light light;
	private Television television;
	private Aircondition aircondition;

	public Facade(Light light, Television television, Aircondition aircondition) {
		this.light = light;
		this.television = television;
		this.aircondition = aircondition;

	}

	// 起床后一键开电器
	public void on() {
		System.out.println("起床了");
		light.on();
		television.on();
		aircondition.on();

	}

	public void off() {
		// 睡觉时一键关电器
		System.out.println("睡觉了");
		light.off();
		television.off();
		aircondition.off();
	}
}
