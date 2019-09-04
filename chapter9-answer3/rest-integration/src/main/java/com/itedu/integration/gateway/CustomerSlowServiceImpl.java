package com.itedu.integration.gateway;

import com.itedu.integration.bean.Customer;

public class CustomerSlowServiceImpl implements CustomerGateway {
    public Customer getCustomerInfo(String customerId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return new Customer("365itedu");
    }
}
