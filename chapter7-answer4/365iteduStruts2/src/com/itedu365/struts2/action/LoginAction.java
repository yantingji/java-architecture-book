package com.itedu365.struts2.action;

import com.itedu365.struts2.framework.action.Action;
import com.itedu365.struts2.framework.action.ActionContext;
import com.itedu365.struts2.framework.action.ValueStack;

public class LoginAction implements Action {

	private String name;

	private String password;

	public String execute() throws Exception {
		System.out.println("***LoginAction execute method***");
		ValueStack valueStack = ActionContext.getContext().getValueStack();

		Object name = valueStack.findValue("name");

		System.out.println(name);
		return "login";
	}

	public String login() {
		System.out.println("***LoginAction login method***");
		String result = "";
		try {
			ValueStack valueStack = ActionContext.getContext().getValueStack();

			Object name = valueStack.findValue("name");

			System.out.println(name);
			// valueStack.setValue("name", );
			// valueStack.setValue("name", "365itedu");
			result = "success";
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
