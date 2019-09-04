package com.itedu.batch;

import org.apache.commons.logging.Log;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.itedu.dao.MasterDao;
import com.itedu.util.LogUtil;

public class Batch1JobExecutionListener extends JobExecutionListenerSupport {
	static Log log = LogUtil.getLog();

	@Autowired
	private MasterDao transactionDataDao;

	public void beforeJob(JobExecution JobExecution) {
		log.info("start method");
		transactionDataDao.delete();
	}

	public void afterJob(JobExecution jobExecution) {
		log.info("end method");
	}
}
