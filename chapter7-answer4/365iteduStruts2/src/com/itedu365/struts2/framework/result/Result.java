package com.itedu365.struts2.framework.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Result {
	protected String path;

	public static Result getInstance(String type, String path) {
		if ("dispatcher".equals(type)) {
			return new DispatcherResult(path);
		} else if ("redirect".equals(type)) {
			return new RedirectResult(path);
		} else {
			return null;
		}
	}

	public Result(String path) {
		this.path = path;
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

}
