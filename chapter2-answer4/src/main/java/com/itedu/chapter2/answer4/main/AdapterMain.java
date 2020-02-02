package com.itedu.chapter2.answer4.main;

import com.itedu.chapter2.answer4.Adaptee;
import com.itedu.chapter2.answer4.ConcreteTarget;
import com.itedu.chapter2.answer4.Target;
import com.itedu.chapter2.answer4.classadapter.ClassAdapter;
import com.itedu.chapter2.answer4.objectadapter.ObjectAdapter;

public class AdapterMain {
	public static void main(String[] args) {
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();
		
		// 使用特殊功能类，即适配类
		Target adapter = new ObjectAdapter(new Adaptee());
		adapter.request();
		
		
		// 使用特殊功能类，即适配类
        Target adapter2 = new ClassAdapter();
        adapter2.request();
	}
}
