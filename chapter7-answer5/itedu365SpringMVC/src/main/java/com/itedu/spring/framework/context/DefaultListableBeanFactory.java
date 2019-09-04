package com.itedu.spring.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.itedu.spring.framework.beans.BeanDefinition;
import com.itedu.spring.framework.beans.BeanWrapper;

public class DefaultListableBeanFactory extends AbstractApplicationContext {

	// 用来存储BeanDefinition的定义
	protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	// 用来存储实例化的单例Bean对象
	protected Map<String, BeanWrapper> singleBeanInstanceMap = new ConcurrentHashMap<>();

	protected List<String> beanDefinitionNames = new ArrayList<>();

	@Override
	protected void refreshBeanFactory() {

	}
}
