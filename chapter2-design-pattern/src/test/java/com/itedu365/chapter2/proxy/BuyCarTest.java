package com.itedu365.chapter2.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itedu365.chapter2.proxy.staticproxy.ProxyCompose;
import com.itedu365.chapter2.proxy.staticproxy.ProxyExtend;
import com.itedu365.chapter2.proxy.staticproxy.Customer;

public class BuyCarTest {

	@Test
	public void test() {
		Customer customer = new Customer();
		customer.setCash(120000);
		ProxyCompose buyCarProxy = new ProxyCompose(customer);
		buyCarProxy.buyCar();

		Customer customer1 = new Customer();
		customer1.setCash(90000);
		ProxyCompose buyCarProxy1 = new ProxyCompose(customer1);
		buyCarProxy1.buyCar();

		ProxyExtend buyCarProxy3 = new ProxyExtend();
		buyCarProxy3.setCash(200000);
		buyCarProxy3.buyCar();
	}

}
