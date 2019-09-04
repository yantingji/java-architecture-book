package com.itedu.spring.integration.gateway;

import com.itedu.spring.integration.bean.Customer;

public interface CustomerGateway {
    public Customer getCustomerInfo(String customerId);
}
