package com.itedu365.chapter2.adapter.classadaptor;

public class V110ToV220Adaptor extends V110 implements V220 {

	@Override
	public String getV220() {
		return convertV110ToV220();
	}

	private String convertV110ToV220() {
		// 拿到源
		String str = outPutV110();
		System.out.println(str + " \n 经过类适配器转化 ");
		// 为这简单起见，这里直接修改源
		str = "输出变成 V220 接口";
		return str;
	}

}
