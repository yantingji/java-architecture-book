package com.itedu.chapter2.answer2.simplefactory.main;

import com.itedu.chapter2.answer2.simplefactory.Fruit;
import com.itedu.chapter2.answer2.simplefactory.factory.Factory;

public class SimpleFactoryMain {

	public static void main(String[] args) {
		Fruit f = Factory.getInstance("com.itedu.chapter2.answer2.simplefactory.Apple");  
        if (f != null) {  
            f.eat();  
        }  

	}

}
