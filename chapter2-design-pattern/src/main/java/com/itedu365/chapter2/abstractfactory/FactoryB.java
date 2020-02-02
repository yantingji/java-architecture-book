package com.itedu365.chapter2.abstractfactory;

public class FactoryB implements Factory{

    @Override
    public Product createProduct() {
        return new ProductB();
    }

    @Override
    public Gift createGift() {
        return new GiftB();
    }

}
