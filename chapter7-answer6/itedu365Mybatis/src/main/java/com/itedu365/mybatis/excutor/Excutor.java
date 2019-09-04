package com.itedu365.mybatis.excutor;

public interface Excutor {
	public <T> Object query(String sqlId, Object parameter);

}
