package com.itedu.chapter2.answer2.simplefactory.factory;

import com.itedu.chapter2.answer2.simplefactory.Fruit;

public class Factory {
	  public static Fruit getInstance(String ClassName) {  
	        Fruit f = null;  
	        try {  
	            f = (Fruit) Class.forName(ClassName).newInstance();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return f;  
	    } 
}
