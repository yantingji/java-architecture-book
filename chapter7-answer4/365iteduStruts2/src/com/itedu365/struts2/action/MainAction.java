package com.itedu365.struts2.action;

public class MainAction {
	private String name;

	public String execute() throws Exception {
		System.out.println("***MainAction execute method***");
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
