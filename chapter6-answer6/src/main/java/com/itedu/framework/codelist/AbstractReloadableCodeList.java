package com.itedu.framework.codelist;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractReloadableCodeList extends AbstractCodeList
		implements ReloadableCodeList, InitializingBean {
	private Map<String, String> exposedMap = null;

	private boolean lazyInit = false;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public final Map<String, String> asMap() {

		if (exposedMap == null) {
			refresh();
		}
		return exposedMap;
	}

	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	@Override
	public final void refresh() {
		if (logger.isDebugEnabled()) {
			logger.debug("refresh codelist codeListId={}", getCodeListId());
		}
		exposedMap = Collections.unmodifiableMap(retrieveMap());
	}

	@Override
	public void afterPropertiesSet() {
		if (!lazyInit) {
			refresh();
		}
	}

	abstract protected Map<String, String> retrieveMap();

}
