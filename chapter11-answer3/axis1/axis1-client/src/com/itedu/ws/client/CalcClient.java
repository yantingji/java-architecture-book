package com.itedu.ws.client;
 
import com.itedu.ws.generated.Calculator;
import com.itedu.ws.generated.CalculatorService;
import com.itedu.ws.generated.CalculatorServiceLocator;
 
public class CalcClient {
    public static void main(String[] args) throws Exception {
        CalculatorService service =  new CalculatorServiceLocator();
 Calculator calc = service.getcalculator();
 System.out.println("15 + 6 = " + calc.add(15, 6));
 System.out.println("15 - 6 = " + calc.subtract(15, 6));
    }
}