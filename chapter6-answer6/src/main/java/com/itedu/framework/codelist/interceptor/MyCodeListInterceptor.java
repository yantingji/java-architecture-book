package com.itedu.framework.codelist.interceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.itedu.framework.codelist.MyCodeList;

public class MyCodeListInterceptor extends HandlerInterceptorAdapter
		implements ApplicationContextAware, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(MyCodeListInterceptor.class);

	private Collection<MyCodeList> myCodeLists;

	private ApplicationContext applicationContext;

	private Pattern codeListIdPattern;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (myCodeLists == null) {
			return;
		}

		for (MyCodeList MyCodeList : myCodeLists) {
			String attributeName = MyCodeList.getCodeListId();
			request.setAttribute(attributeName, MyCodeList.asMap());
		}
	}

	@Override
	public void afterPropertiesSet() {

		Assert.notNull(applicationContext, "applicationContext is null.");

		if (this.codeListIdPattern == null) {
			this.codeListIdPattern = Pattern.compile(".+");
		}

		Map<String, MyCodeList> definedMyCodeLists = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				MyCodeList.class, false, false);
		Map<String, MyCodeList> targetMyCodeLists = new HashMap<String, MyCodeList>();
		for (MyCodeList MyCodeList : definedMyCodeLists.values()) {
			String MyCodeListId = MyCodeList.getCodeListId();
			if (MyCodeListId != null) {
				Matcher MyCodeListIdMatcher = this.codeListIdPattern.matcher(MyCodeListId);
				if (MyCodeListIdMatcher.matches()) {
					targetMyCodeLists.put(MyCodeListId, MyCodeList);
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("registered MyCodeList : {}", targetMyCodeLists.keySet());
		}

		this.myCodeLists = Collections.unmodifiableCollection(targetMyCodeLists.values());

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setCodeListIdPattern(Pattern MyCodeListIdPattern) {
		this.codeListIdPattern = MyCodeListIdPattern;
	}

	protected Collection<MyCodeList> getMyCodeLists() {
		return myCodeLists;
	}

}
