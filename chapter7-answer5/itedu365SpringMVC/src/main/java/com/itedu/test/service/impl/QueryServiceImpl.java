package com.itedu.test.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itedu.spring.framework.annotation.Service;
import com.itedu.test.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService {

	public String query(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
		return json;
	}

}
