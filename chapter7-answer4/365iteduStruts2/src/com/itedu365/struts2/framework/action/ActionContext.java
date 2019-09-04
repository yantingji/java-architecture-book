package com.itedu365.struts2.framework.action;

import java.util.HashMap;
import java.util.Map;

public class ActionContext {
	@SuppressWarnings("rawtypes")
	static ThreadLocal actionContext = new ThreadLocal();

	Map<String, Object> context = new HashMap<String, Object>();

	public ActionContext(ValueStack valueStack) {
		setValueStack(valueStack);
	}

	public static ActionContext getContext() {
		return (ActionContext) actionContext.get();

	}

	@SuppressWarnings("unchecked")
	public static void setContext(ActionContext context) {
		actionContext.set(context);
	}

	public void put(String key, Object value) {
		context.put(key, value);
	}

	public Object get(String key) {
		return context.get(key);
	}

	public void setValueStack(ValueStack stack) {
		put("VALUE_STACk", stack);
	}

	public ValueStack getValueStack() {
		return (ValueStack) get("VALUE_STACk");
	}
}
