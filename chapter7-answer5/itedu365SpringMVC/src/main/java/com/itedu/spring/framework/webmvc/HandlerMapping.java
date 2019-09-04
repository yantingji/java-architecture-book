package com.itedu.spring.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class HandlerMapping {
	private Object controller;
	private Method method;
	private Pattern urlPattern;

	public HandlerMapping(Object controller, Method method, Pattern urlPattern) {
		this.controller = controller;
		this.method = method;
		this.urlPattern = urlPattern;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Pattern getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(Pattern urlPattern) {
		this.urlPattern = urlPattern;
	}
}
