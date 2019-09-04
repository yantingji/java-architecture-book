package com.itedu.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.itedu.dao.mapper.MasterMapper;
import com.itedu.entity.RankFileEntity;

@Component
public class MasterDao extends SqlSessionDaoSupport {
	public void insert(RankFileEntity rankFileEntity) {
		SqlSession session = getSqlSession();
		MasterMapper mapper = session.getMapper(MasterMapper.class);
		mapper.insert(rankFileEntity.getId(), rankFileEntity.getRank());
	}

	public void delete() {
		SqlSession session = getSqlSession();
		MasterMapper mapper = session.getMapper(MasterMapper.class);
		mapper.delete();
	}
}
