package com.itedu365.struts2.framework.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherResult extends Result {

	public DispatcherResult(String path) {
		super(path);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
