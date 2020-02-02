package com.itedu365.chapter2.adapter.objectadaptor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itedu365.chapter2.adapter.objectadaptor.Projector;
import com.itedu365.chapter2.adapter.objectadaptor.V110ToV220Adaptor;
import com.itedu365.chapter2.adapter.objectadaptor.V220;

public class ProjectorTest {

	@Test
	public void test() {
		// 投影仪
		Projector projector = new Projector();
		// 适配器
		V220 adapter = new V110ToV220Adaptor(new V110());
		// 最后得到投影仪想要的V220即可
		String str = projector.getV220(adapter);
		System.out.println(str);
	}

}
