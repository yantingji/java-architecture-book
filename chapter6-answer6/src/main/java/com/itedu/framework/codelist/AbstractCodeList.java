package com.itedu.framework.codelist;

import org.springframework.beans.factory.BeanNameAware;

public abstract class AbstractCodeList implements MyCodeList, BeanNameAware {
	private String codeListId;

	@Override
	public void setBeanName(String beanName) {
		this.codeListId = beanName;
	}

	@Override
	public String getCodeListId() {
		return codeListId;
	}

}
