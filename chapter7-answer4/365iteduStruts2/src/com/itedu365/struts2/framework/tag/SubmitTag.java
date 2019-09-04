package com.itedu365.struts2.framework.tag;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itedu365.struts2.framework.config.ActionConfig;

public class SubmitTag extends SimpleTagSupport {
	private String id;
	private String type;
	private String value;
	private String method;

	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) this.getJspContext();

		JspWriter out = getJspContext().getOut();

		ServletRequest servletRequst = pageContext.getRequest();
		ActionConfig actionConfig = (ActionConfig) servletRequst.getAttribute("actionConfig");
		if (actionConfig != null) {
			actionConfig.setMethodName(method);
		}
		String inputStr = "<input id= \"" + id + "\"" + " type= \"" + type + "\"" + " value= \" " + value + "\" />";
		out.print(inputStr);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
