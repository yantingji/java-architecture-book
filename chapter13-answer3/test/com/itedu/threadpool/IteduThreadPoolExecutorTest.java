package com.itedu.threadpool;

import java.util.concurrent.ExecutorService;

import org.junit.Test;

public class IteduThreadPoolExecutorTest {

	@Test
	public void test() {
		IteduThreadPoolExecutor exec = new IteduThreadPoolExecutor();
		// 1.初始化
		exec.init();

		ExecutorService pool = exec.getCustomThreadPoolExecutor();
		for (int i = 1; i < 30; i++) {
			System.out.println("提交第" + i + "个任务!");
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("running=====");
				}
			});
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
