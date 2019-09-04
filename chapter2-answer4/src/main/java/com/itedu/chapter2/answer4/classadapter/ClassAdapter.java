package com.itedu.chapter2.answer4.classadapter;

import com.itedu.chapter2.answer4.Adaptee;
import com.itedu.chapter2.answer4.Target;

public class ClassAdapter extends Adaptee implements Target{

	public void request() {
		super.specificRequest();
	}

}
