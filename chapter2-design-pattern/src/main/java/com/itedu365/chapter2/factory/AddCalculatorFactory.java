package com.itedu365.chapter2.factory;

public class AddCalculatorFactory  implements Factory {

    @Override
    public Calculator createCalculator() {
        return new AddCalculator();
    }

}
