package com.itedu.entity.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.itedu.entity.RankFileEntity;

public class RankFileEntityFieldSetMapper implements
		FieldSetMapper<RankFileEntity> {
	public RankFileEntity mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null) {
			return null;
		}
		RankFileEntity entity = new RankFileEntity();
		entity.setId(fieldSet.readString("id"));
		entity.setRank(fieldSet.readString("rank"));
		return entity;
	}
}
