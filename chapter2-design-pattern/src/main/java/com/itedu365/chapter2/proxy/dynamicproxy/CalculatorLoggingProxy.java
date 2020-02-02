package com.itedu365.chapter2.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorLoggingProxy {
  //要代理的对象
    private Calculator target;
    
    public CalculatorLoggingProxy(Calculator target){
        this.target = target;
    }
    
    public Calculator getLoggingProxy(){
        
        Calculator proxyObject = null;
        InvocationHandler h = new InvocationHandler() {
            /**
             * proxy：正在返回的那个代理对象，一般情况下，在invoke方法中都不使用该对象。
             * method：正在被调用的方法
             * args：调用方法时，传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                System.out.println("被调用的方法名："+method.getName());
                
                //执行的方法
                Object result = method.invoke(target, args);
                
                return result;
            }
        };
        
        proxyObject = (Calculator) Proxy.newProxyInstance( target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(), h);
        
        return proxyObject;

    }


}
