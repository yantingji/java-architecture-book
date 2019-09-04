package com.itedu365.struts2.framework.action;

public interface Action {
	public static String SUCCESS = "success";

	public static String ERROR = "error";

	public static String INPUT = "input";

	public String execute() throws Exception;
}
