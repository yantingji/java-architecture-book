package com.itedu365.chapter2.proxy.dynamicproxy;

public class Client {
    public static void main(String[] args) {
        Calculator target = new RealCalculatorImpl();
        Calculator proxy = new CalculatorLoggingProxy(target).getLoggingProxy();
        
        int result = proxy.add(1, 2);
        System.out.println(result);
    }

}
