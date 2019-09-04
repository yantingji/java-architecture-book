package com.itedu365.struts2.framework.invocation;

import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.itedu365.struts2.framework.action.ActionContext;
import com.itedu365.struts2.framework.action.ValueStack;
import com.itedu365.struts2.framework.config.ActionConfig;
import com.itedu365.struts2.framework.interceptor.base.Interceptor;

public class ActionInvocation {
	private LinkedList<Interceptor> interceptors;

	private HttpServletRequest request;

	public ActionInvocation(LinkedList<Interceptor> interceptors, HttpServletRequest request) {
		this.interceptors = interceptors;
		this.request = request;
	}

	int index = -1;

	public String invoke() throws Exception {
		String result = "";
		String methodName = "execute";
		ActionConfig actionConfig = (ActionConfig) request.getAttribute("actionConfig");
		String actionClass = actionConfig.getClazz();
		Class<?> clazz = Class.forName(actionClass);
		Object action = clazz.newInstance();
		ActionContext actionContext = ActionContext.getContext();
		ValueStack valueStack = actionContext.getValueStack();
		valueStack.push(action);
		if (null != (actionConfig.getMethodName()) && !"".equals(actionConfig.getMethodName())) {
			methodName = actionConfig.getMethodName();
		}

		index++;
		if (index >= this.interceptors.size()) {
			Method method = clazz.getMethod(methodName, null);
			if (method != null)
				result = (String) method.invoke(action, new Object[] {});
			actionConfig.setMethodName(null);
		} else {
			this.interceptors.get(index).setRequest(request);
			result = this.interceptors.get(index).intercept(this);
		}

		return result;
	}
}
