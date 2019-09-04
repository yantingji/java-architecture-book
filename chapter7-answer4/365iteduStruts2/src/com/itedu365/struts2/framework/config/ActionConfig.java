package com.itedu365.struts2.framework.config;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	private String actionName;
	private String methodName;
	private String clazz;

	private Map<String, ResultConfig> resultConfigMap = new HashMap<String, ResultConfig>();

	public void addResultConfig(ResultConfig resultConfig) {
		resultConfigMap.put(resultConfig.getName(), resultConfig);
	}

	public ResultConfig getResultConfig(String name) {
		return resultConfigMap.get(name);
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
