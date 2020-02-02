package com.itedu365.chapter2.singleton;

/**
 * 单例懒汉线程安全模式
 */
public class Singleton {
	private static volatile Singleton singleton;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
