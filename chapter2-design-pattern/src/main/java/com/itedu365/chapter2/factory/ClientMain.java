package com.itedu365.chapter2.factory;

public class ClientMain {
    public static void main(String[] args) {
        Factory factory;
        Calculator calculator;
                 factory = new AddCalculatorFactory();
                 calculator= factory.createCalculator();
                System.out.println(calculator.calculate(1, 2)); 
    }
}
