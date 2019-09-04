package com.itedu.chapter2.answer3.factory;

import com.itedu.chapter2.answer3.Fruit;

public class Factory {
	
	public static Fruit getInstance(String className) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
