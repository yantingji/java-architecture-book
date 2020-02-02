package com.itedu365.chapter2.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itedu365.chapter2.simplefactory.Calculator;
import com.itedu365.chapter2.simplefactory.CalculatorFactory;

public class CalculatorFactoryTest {

	@Test
	public void test() {
		CalculatorFactory factory = new CalculatorFactory();
		Calculator add = factory.produce("add");

		int result = add.calculate(1, 2);
		assertEquals(3, result);
	}

}
