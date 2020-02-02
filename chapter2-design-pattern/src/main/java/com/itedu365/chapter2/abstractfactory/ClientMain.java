package com.itedu365.chapter2.abstractfactory;

public class ClientMain {
    public static void main(String[] args) {
        Factory factory;
        factory = new FactoryA();
        Product productA= factory.createProduct();
        Gift gift =factory.createGift();
        productA.product();
        gift.getGift();
    }
}
