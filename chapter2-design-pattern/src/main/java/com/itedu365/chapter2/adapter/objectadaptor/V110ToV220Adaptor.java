package com.itedu365.chapter2.adapter.objectadaptor;

/**
 * 适配器，把V110转换成V220
 */
public class V110ToV220Adaptor implements V220 {

	private V110 v110;

	public V110ToV220Adaptor() {
	}

	public V110ToV220Adaptor(V110 v110) {
		this.v110 = v110;
	}

	@Override
	public String getV220() {
		return convertV110ToV220();
	}

	private String convertV110ToV220() {
		// 拿到源
		String str = v110.outPutV110();
		System.out.println(str + " \n 经过对象适配器转化 ");
		// 为这简单起见，这里直接修改源
		str = "输出变成 V220 接口";
		return str;
	}

}
