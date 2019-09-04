package com.itedu.chapter2.answer2.abstractfactory.main;

import com.itedu.chapter2.answer2.abstractfactory.AbstractFactory;
import com.itedu.chapter2.answer2.abstractfactory.color.Color;
import com.itedu.chapter2.answer2.abstractfactory.factory.FactoryProducer;
import com.itedu.chapter2.answer2.abstractfactory.shape.Shape;

public class AbstractFactoryPatternMain {
	public static void main(String[] args) {
		 
	      //获取形状工厂
	      AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
	 
	      //获取形状为 Rectangle 的对象
	      Shape shape1 = shapeFactory.getShape("RECTANGLE");
	 
	      //调用 Rectangle 的 draw 方法
	      shape1.draw();
	      
	      //获取形状为 Square 的对象
	      Shape shape2 = shapeFactory.getShape("SQUARE");
	 
	      //调用 Square 的 draw 方法
	      shape2.draw();
	 
	      //获取颜色工厂
	      AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
	 
	      //获取颜色为 Green 的对象
	      Color color1 = colorFactory.getColor("Green");
	 
	      //调用 Green 的 fill 方法
	      color1.fill();
	 
	      //获取颜色为 Blue 的对象
	      Color color2 = colorFactory.getColor("BLUE");
	 
	      //调用 Blue 的 fill 方法
	      color2.fill();
	   }
}
