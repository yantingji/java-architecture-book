/**
 * CalculatorSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.itedu.ws.generated;
import com.itedu.ws.server.SimpleCalculator;
public class CalculatorSoapBindingImpl implements com.itedu.ws.generated.Calculator{
    private SimpleCalculator calc = new SimpleCalculator();
    public int add(int a, int b) throws java.rmi.RemoteException {
        return calc.add(a, b);
    }
    public int subtract(int from, int x) throws java.rmi.RemoteException {
        return calc.subtract(from, x);
    }

}
