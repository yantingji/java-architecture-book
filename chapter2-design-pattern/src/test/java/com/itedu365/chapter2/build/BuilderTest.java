package com.itedu365.chapter2.build;

import org.junit.Test;

public class BuilderTest {

	@Test
	public void test() {
		Builder builder = new AssemblerBuilder();
		Direcror direcror = new Direcror(builder);
		Computer computer = direcror.createComputer("Intel 酷睿i7", "三星M9T 2TB", "技嘉AORUS", "科赋Cras II");
		System.out.println("电脑使用的是：\n" + computer.getMainBoard() + " 主板\n" + computer.getCpu() + " CPU\n"
				+ computer.getHardDisk() + "硬盘\n" + computer.getMainBoard() + " 内存\n");
	}

}
