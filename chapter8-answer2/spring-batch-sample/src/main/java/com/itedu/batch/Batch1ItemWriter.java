package com.itedu.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itedu.dao.MasterDao;
import com.itedu.entity.RankFileEntity;
import com.itedu.util.LogUtil;

@Component("batch1Writer")
public class Batch1ItemWriter implements ItemWriter<RankFileEntity> {
	static Log log = LogUtil.getLog();

	@Autowired
	private MasterDao transactionDataDao;

	public void write(List<? extends RankFileEntity> data) throws Exception {
		log.info("start method");
		transactionDataDao.insert(data.get(0));
	}
}
