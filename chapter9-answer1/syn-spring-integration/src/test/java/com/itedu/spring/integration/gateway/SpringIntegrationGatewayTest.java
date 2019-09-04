package com.itedu.spring.integration.gateway;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itedu.spring.integration.bean.Customer;
import com.itedu.spring.integration.gateway.CustomerGateway;

public class SpringIntegrationGatewayTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        try {
            CustomerGateway customerService = (CustomerGateway) context.getBean("customerService"); 
            System.out.println("Get Customer info synchronously");
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
