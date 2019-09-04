/**
 * CalculatorService.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.)
 */

package com.itedu.ws.generated;

public interface CalculatorService extends javax.xml.rpc.Service {
    public java.lang.String getcalculatorAddress();

    public com.itedu.ws.generated.Calculator getcalculator() throws javax.xml.rpc.ServiceException;

    public com.itedu.ws.generated.Calculator getcalculator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
