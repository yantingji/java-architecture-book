package com.itedu.batch.exception;

import org.apache.commons.logging.Log;

import com.itedu.util.LogUtil;

public class Batch1SkipException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static Log log = LogUtil.getLog();

	public Batch1SkipException(String message) {
		log.warn(message);
	}
}
