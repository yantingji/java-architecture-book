package com.itedu365.chapter2.build;

public class Direcror {
	private Builder builder;

	public Direcror(Builder builder) {
		this.builder = builder;
	}

	public Computer createComputer(String cpu, String hardDisk, String mainBoard, String memory) {
		this.builder.createMainBoard(mainBoard);
		this.builder.createCpu(cpu);
		this.builder.createMemory(memory);
		this.builder.createhardDisk(hardDisk);
		return this.builder.createComputer();
	}
}
