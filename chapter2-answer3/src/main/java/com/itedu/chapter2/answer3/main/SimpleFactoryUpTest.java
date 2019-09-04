package com.itedu.chapter2.answer3.main;

import java.io.IOException;
import java.util.Properties;

import com.itedu.chapter2.answer3.Fruit;
import com.itedu.chapter2.answer3.factory.Factory;
import com.itedu.chapter2.answer3.utils.Utils;

public class SimpleFactoryUpTest {
	public static void main(String[] a) throws IOException {
        Properties pro = Utils.getPro();
        Fruit f = Factory.getInstance(pro.getProperty("apple"));
        if (f != null) {
            f.eat();
        }
    }
}
