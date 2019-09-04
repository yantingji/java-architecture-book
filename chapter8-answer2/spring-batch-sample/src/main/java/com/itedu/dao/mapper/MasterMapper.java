package com.itedu.dao.mapper;

import org.apache.ibatis.annotations.Param;

public interface MasterMapper {
	void insert(@Param("easyId") String easyId, @Param("rank") String rank);

	void delete();
}