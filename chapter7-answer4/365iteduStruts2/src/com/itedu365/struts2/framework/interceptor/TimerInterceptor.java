package com.itedu365.struts2.framework.interceptor;

import java.util.Calendar;

import com.itedu365.struts2.framework.interceptor.base.AbstractInterceptor;
import com.itedu365.struts2.framework.invocation.ActionInvocation;

public class TimerInterceptor extends AbstractInterceptor {

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) {
		String result = null;
		try {
			Long eventStartTime;
			Long eventEndTime;
			Calendar calendar1 = Calendar.getInstance();
			eventStartTime = calendar1.getTimeInMillis();
			System.out.println("TimerInterceptor Before");

			result = invocation.invoke();
			Thread.sleep(100);

			Calendar calendar2 = Calendar.getInstance();
			eventEndTime = calendar2.getTimeInMillis();
			System.out.println("Action execute time: " + (eventEndTime - eventStartTime) + " millisecond.");
			System.out.println("TimerInterceptor After");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void destroy() {
	}
}
