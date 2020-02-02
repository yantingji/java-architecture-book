package com.itedu365.chapter2.abstractfactory;

public class FactoryA implements Factory{

    @Override
    public Product createProduct() {
        return new ProductA();
    }

    @Override
    public Gift createGift() {
        return new GiftA();
    }

}
