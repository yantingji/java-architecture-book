package com.itedu365.springframework.context.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 对xml配置文件中 bean标签的属性的封装
 * 
 * 
 */

public class BeanDefinition {

	private String id; // id属性
	private String clazz; // class属性

	private List<PropertyDefinition> propertys = new ArrayList<PropertyDefinition>();

	// bean子标签对象集合

	public BeanDefinition(String id, String clazz) {
		this.id = id;
		this.clazz = clazz;
	}

	public String getClazz() {
		return clazz;
	}

	public String getId() {
		return id;
	}

	public List<PropertyDefinition> getPropertys() {
		return propertys;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPropertys(List<PropertyDefinition> propertys) {
		this.propertys = propertys;
	}

}
