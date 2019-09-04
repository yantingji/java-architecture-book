package com.itedu365.struts2.framework.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.itedu365.struts2.framework.action.ActionContext;

public class Itedu365RequestWrapper extends HttpServletRequestWrapper {

	public Itedu365RequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Object getAttribute(String name) {
		Object obj = super.getAttribute(name);
		if (obj != null) {
			return obj;
		}
		return ActionContext.getContext().getValueStack().findValue(name);
	}
}
