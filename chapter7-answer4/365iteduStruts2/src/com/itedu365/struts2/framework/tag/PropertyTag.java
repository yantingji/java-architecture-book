package com.itedu365.struts2.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itedu365.struts2.framework.action.ActionContext;
import com.itedu365.struts2.framework.action.ValueStack;

public class PropertyTag extends SimpleTagSupport {
	private String value;

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		ValueStack valueStack = ActionContext.getContext().getValueStack();

		String actualValue = null;
		actualValue = (String) valueStack.findValue(value);
		out.print(actualValue);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
