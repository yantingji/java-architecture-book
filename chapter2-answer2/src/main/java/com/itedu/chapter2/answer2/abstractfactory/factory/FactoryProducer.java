package com.itedu.chapter2.answer2.abstractfactory.factory;

import com.itedu.chapter2.answer2.abstractfactory.AbstractFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice){
	      if(choice.equalsIgnoreCase("SHAPE")){
	         return new ShapeFactory();
	      } else if(choice.equalsIgnoreCase("COLOR")){
	         return new ColorFactory();
	      }
	      return null;
	   }
}
