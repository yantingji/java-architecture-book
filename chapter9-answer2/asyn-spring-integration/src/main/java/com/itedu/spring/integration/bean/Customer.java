package com.itedu.spring.integration.bean;

public class Customer {
    private String id;

    public Customer(String id) {
        super();
        this.id = id;
    }

    public String toString() {
        return "Customer: " + id;
    }
}
