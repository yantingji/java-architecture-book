package com.itedu.spring.framework.context;

public abstract class AbstractApplicationContext {
	protected void onRefresh() {
	}

	protected abstract void refreshBeanFactory();
}
