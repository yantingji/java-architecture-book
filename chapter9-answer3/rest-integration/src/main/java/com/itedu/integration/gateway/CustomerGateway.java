package com.itedu.integration.gateway;

import com.itedu.integration.bean.Customer;

public interface CustomerGateway {
    public Customer getCustomerInfo(String customerId);
}
