package com.itedu.aspect.log;

public class TransactionDemo {
	//前置通知
    public void startTransaction(){
        System.out.println("begin transaction ");
    }
    
    //后置通知
    public void commitTransaction(){
        System.out.println("commit transaction ");
    }
    
    
}
