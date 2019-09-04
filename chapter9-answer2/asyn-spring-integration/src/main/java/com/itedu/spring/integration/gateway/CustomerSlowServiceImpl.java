package com.itedu.spring.integration.gateway;

import com.itedu.spring.integration.bean.Customer;

public class CustomerSlowServiceImpl implements CustomerGateway {
    public Customer getCustomerInfo(String customerId) {
        //Simulate a delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return new Customer(customerId + ", called by: " + Thread.currentThread().getName());
    }
}
