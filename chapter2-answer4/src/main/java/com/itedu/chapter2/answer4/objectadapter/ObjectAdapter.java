package com.itedu.chapter2.answer4.objectadapter;

import com.itedu.chapter2.answer4.Adaptee;
import com.itedu.chapter2.answer4.Target;

public class ObjectAdapter implements Target{
	// 直接关联被适配类
		private Adaptee adaptee;
		 // 可以通过构造函数传入具体需要适配的被适配类对象
	    public ObjectAdapter (Adaptee adaptee) {
	        this.adaptee = adaptee;
	    }
		
		public void request() {
			// 这里是使用委托的方式完成特殊功能
			this.adaptee.specificRequest();
		}

}
