package com.itedu365.mybatis.sqlsession;

import java.lang.reflect.Proxy;

import com.itedu365.mybatis.excutor.Excutor;
import com.itedu365.mybatis.excutor.ExcutorImpl;
import com.itedu365.mybatis.excutor.MapperProxyHandler;

public class SqlSession {
	protected SqlSession() {
		
	}
	private Excutor excutor = new ExcutorImpl();

	public <T> Object selectOne(String statement, String parameter) {
		return excutor.query(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> cls) {
		return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, new MapperProxyHandler(this));
	}
}
