package com.itedu365.chapter2.build;

public class AssemblerBuilder implements Builder {

	private Computer computer = new Computer();

	@Override
	public void createCpu(String cpu) {
		computer.setCpu(cpu);
	}

	@Override
	public void createhardDisk(String hardDisk) {
		computer.setHardDisk(hardDisk);
	}

	@Override
	public void createMainBoard(String mainBoard) {
		computer.setMainBoard(mainBoard);
	}

	@Override
	public void createMemory(String memory) {
		computer.setMemory(memory);
	}

	@Override
	public Computer createComputer() {
		return computer;
	}
}
