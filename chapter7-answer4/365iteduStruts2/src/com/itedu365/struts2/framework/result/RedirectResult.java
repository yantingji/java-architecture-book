package com.itedu365.struts2.framework.result;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectResult extends Result {

	public RedirectResult(String path) {
		super(path);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			path = request.getContextPath() + path;
			response.sendRedirect(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
