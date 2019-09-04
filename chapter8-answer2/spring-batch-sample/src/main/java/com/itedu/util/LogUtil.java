package com.itedu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {
	static Log log = LogFactory.getLog(LogUtil.class);

	private LogUtil() {
	}

	public static Log getLog() {
		return log;
	}
}
