package com.itedu.batch;

import org.apache.commons.logging.Log;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.itedu.batch.exception.Batch1SkipException;
import com.itedu.entity.RankFileEntity;
import com.itedu.util.LogUtil;
@Component("batch1Processor")
public class Batch1ItemProcessor implements
		ItemProcessor<RankFileEntity, RankFileEntity> {
	static Log log = LogUtil.getLog();

	public RankFileEntity process(RankFileEntity ｒankFileEntity)
			throws Exception {
		log.info("start method");

		if (ｒankFileEntity.getRank().matches("^[A-F]+$")) {
			throw new Batch1SkipException("Rank超出范围：["
					+ ｒankFileEntity.getId() + "]");
		}
		return ｒankFileEntity;
	}
}
