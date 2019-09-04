package com.itedu.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.batch.core.listener.ItemListenerSupport;

import com.itedu.entity.RankFileEntity;
import com.itedu.util.LogUtil;

public class Batch1ItemListener extends
		ItemListenerSupport<Object, RankFileEntity> {
	static Log log = LogUtil.getLog();

	public void onWriteError(Exception ex, List<? extends RankFileEntity> item) {

		try {
			if (ex.getClass() == this
					.getClass()
					.getClassLoader()
					.loadClass(
							"org.springframework.dao.DataIntegrityViolationException")) {
				log.error("データ不正によるINSERTエラー。スキップする。（スキップの定義はmodule-context.xml）");

				ex.printStackTrace();
			} else {
				// スキップしない
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
