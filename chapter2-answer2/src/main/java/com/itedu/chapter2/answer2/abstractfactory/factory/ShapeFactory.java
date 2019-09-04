package com.itedu.chapter2.answer2.abstractfactory.factory;

import com.itedu.chapter2.answer2.abstractfactory.AbstractFactory;
import com.itedu.chapter2.answer2.abstractfactory.color.Color;
import com.itedu.chapter2.answer2.abstractfactory.shape.Rectangle;
import com.itedu.chapter2.answer2.abstractfactory.shape.Shape;
import com.itedu.chapter2.answer2.abstractfactory.shape.Square;

public class ShapeFactory extends AbstractFactory {
    
   @Override
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }        
      if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
      } else if(shapeType.equalsIgnoreCase("SQUARE")){
         return new Square();
      }
      return null;
   }
   
   @Override
   public Color getColor(String color) {
      return null;
   }

}
