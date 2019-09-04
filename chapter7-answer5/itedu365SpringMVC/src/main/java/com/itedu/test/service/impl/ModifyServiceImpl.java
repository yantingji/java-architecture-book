package com.itedu.test.service.impl;

import com.itedu.spring.framework.annotation.Service;
import com.itedu.test.service.ModifyService;

@Service
public class ModifyServiceImpl implements ModifyService {

	public String add(String name, String addr) {
		return "modifyService add,name=" + name + ",addr=" + addr;
	}

	public String edit(Integer id, String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	public String remove(Integer id) {
		return "modifyService id=" + id;
	}

}
