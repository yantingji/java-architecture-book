package com.itedu365.struts2.framework.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itedu365.struts2.framework.action.ActionContext;
import com.itedu365.struts2.framework.action.ActionProxy;
import com.itedu365.struts2.framework.action.ValueStack;
import com.itedu365.struts2.framework.config.ActionConfig;
import com.itedu365.struts2.framework.config.ResultConfig;
import com.itedu365.struts2.framework.result.Result;
import com.itedu365.struts2.framework.wrapper.Itedu365RequestWrapper;

public class StrutsPrepareAndExecuteFilter implements Filter {
	private PrepareOperations prepareOperations;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		InputStream is = StrutsPrepareAndExecuteFilter.class.getClassLoader().getResourceAsStream("struts2.xml");
		prepareOperations = PrepareOperations.getInstance(is);
		// test code start
		System.out.println(prepareOperations.getInterceptors().getFirst().getClass());
		System.out.println(prepareOperations.getInterceptors().getLast().getClass());
		System.out.println(prepareOperations.getActionConfig("login").getActionName());
		System.out.println(prepareOperations.getActionConfig("redirectLogin").getActionName());
		// test code end
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			try {

				ValueStack valueStack = new ValueStack();
				ActionContext ctx = new ActionContext(valueStack);
				ActionContext.setContext(ctx);
				HttpServletRequest servletRequest = (HttpServletRequest) request;

				String uri = servletRequest.getRequestURI();
				String actionName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".action"));
				ActionConfig actionConfig = prepareOperations.getActionConfig(actionName);
				servletRequest.setAttribute("actionName", actionName);
				ActionProxy actionProxy = new ActionProxy(servletRequest, prepareOperations);

				String resultName = actionProxy.execute();
				ResultConfig resultConfig = actionConfig.getResultConfig(resultName);

				String path = resultConfig.getValue();
				String type = resultConfig.getType();

				Result.getInstance(type, path).execute(new Itedu365RequestWrapper((HttpServletRequest) request),
						(HttpServletResponse) response);

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
	}
}
