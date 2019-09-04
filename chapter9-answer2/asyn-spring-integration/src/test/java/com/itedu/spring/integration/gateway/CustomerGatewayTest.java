package com.itedu.spring.integration.gateway;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itedu.spring.integration.bean.Customer;

public class CustomerGatewayTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        try {
            CustomerGateway customerService = (CustomerGateway) context.getBean("customerServiceByDispatcher"); 
            System.out.println("Get Customer info asynchronously using dispatcher");
            for (int i = 0; i < 3; i++) {
                String id = "C0" + i;
                System.out.println("Id: " + id);
                Customer customer = customerService.getCustomerInfo(id);
                System.out.println("Customer information for: " + id + "(" + customer + ")");
            }
        } finally {
            context.close();
        }
	}

}
