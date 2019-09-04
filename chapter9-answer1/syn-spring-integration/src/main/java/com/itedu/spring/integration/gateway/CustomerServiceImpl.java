package com.itedu.spring.integration.gateway;

import com.itedu.spring.integration.bean.Customer;

public class CustomerServiceImpl implements CustomerGateway {
    public Customer getCustomerInfo(String customerId) {
        return new Customer(customerId + ", called by: " + Thread.currentThread().getName());
    }
}
