package com.itedu365.chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.itedu365.chapter2.proxy.dynamicproxy.DynamicProxy;
import com.itedu365.chapter2.proxy.staticproxy.BuyCar;
import com.itedu365.chapter2.proxy.staticproxy.Customer;

public class DynamicProxyTest {

	@Test
	public void test() {
		// 我们要代理的真实对象
		Customer customer = new Customer();
		customer.setCash(80000);
		// 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
		InvocationHandler handler = new DynamicProxy(customer);

		/*
		 * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
		 * 第一个参数handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
		 * 第二个参数customer.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，
		 * 表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
		 * 第三个参数handler， 我们这里将这个代理对象关联到了上方的
		 * InvocationHandler 这个对象上
		 */
		BuyCar buyCar = (BuyCar) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				customer.getClass().getInterfaces(), handler);
		buyCar.buyCar();
	}

}
