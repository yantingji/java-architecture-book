package com.itedu365.chapter2.proxy.dynamicproxy;

public class RealCalculatorImpl implements Calculator{
    @Override
    public int add(int i, int j) {
        int result = i + j;
        return result;
    }
 
    @Override
    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

}
