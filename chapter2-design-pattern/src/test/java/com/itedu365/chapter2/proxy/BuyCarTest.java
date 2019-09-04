package com.itedu365.chapter2.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itedu365.chapter2.proxy.staticproxy.BuyCarProxy1;
import com.itedu365.chapter2.proxy.staticproxy.BuyCarProxy2;
import com.itedu365.chapter2.proxy.staticproxy.Customer;

public class BuyCarTest {

	@Test
	public void test() {
		Customer customer = new Customer();
		customer.setCash(120000);
		BuyCarProxy1 buyCarProxy = new BuyCarProxy1(customer);
		buyCarProxy.buyCar();

		Customer customer1 = new Customer();
		customer1.setCash(90000);
		BuyCarProxy1 buyCarProxy1 = new BuyCarProxy1(customer1);
		buyCarProxy1.buyCar();

		BuyCarProxy2 buyCarProxy3 = new BuyCarProxy2();
		buyCarProxy3.setCash(200000);
		buyCarProxy3.buyCar();
	}

}
