package com.itedu365.chapter2.simplefactory;

public class CalculatorFactory {
	public Calculator produce(String type) {

		if ("add".equals(type)) {
			return new AddCalculator();
		} else if ("subtraction".equals(type)) {
			return new SubtractionCalculator();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}
}
