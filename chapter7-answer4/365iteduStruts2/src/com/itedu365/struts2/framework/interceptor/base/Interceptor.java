package com.itedu365.struts2.framework.interceptor.base;

import javax.servlet.http.HttpServletRequest;

import com.itedu365.struts2.framework.invocation.ActionInvocation;

public interface Interceptor {

	public void init();

	public String intercept(ActionInvocation invocation);

	public void setRequest(HttpServletRequest request);

	public void destroy();
}
