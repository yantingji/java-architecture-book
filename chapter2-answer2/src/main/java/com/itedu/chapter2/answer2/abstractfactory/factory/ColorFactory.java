package com.itedu.chapter2.answer2.abstractfactory.factory;

import com.itedu.chapter2.answer2.abstractfactory.AbstractFactory;
import com.itedu.chapter2.answer2.abstractfactory.color.Blue;
import com.itedu.chapter2.answer2.abstractfactory.color.Color;
import com.itedu.chapter2.answer2.abstractfactory.color.Green;
import com.itedu.chapter2.answer2.abstractfactory.shape.Shape;

public class ColorFactory extends AbstractFactory {
    
   @Override
   public Shape getShape(String shapeType){
      return null;
   }
   
   @Override
   public Color getColor(String color) {
      if(color == null){
         return null;
      }        
      if(color.equalsIgnoreCase("GREEN")){
         return new Green();
      } else if(color.equalsIgnoreCase("BLUE")){
         return (Color) new Blue();
      }
      return null;
   }

}
