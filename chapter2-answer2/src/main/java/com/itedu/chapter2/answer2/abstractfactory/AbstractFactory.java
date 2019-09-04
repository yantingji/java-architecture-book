package com.itedu.chapter2.answer2.abstractfactory;

import com.itedu.chapter2.answer2.abstractfactory.color.Color;
import com.itedu.chapter2.answer2.abstractfactory.shape.Shape;

public abstract  class AbstractFactory {
	 public abstract Color getColor(String color);
	 public abstract Shape getShape(String shape) ;
}
