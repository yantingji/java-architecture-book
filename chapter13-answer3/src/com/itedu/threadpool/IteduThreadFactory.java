package com.itedu.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class IteduThreadFactory implements ThreadFactory {

	private AtomicInteger count = new AtomicInteger(0);

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		String threadName = IteduThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
		System.out.println(threadName);
		t.setName(threadName);
		return t;
	}
}