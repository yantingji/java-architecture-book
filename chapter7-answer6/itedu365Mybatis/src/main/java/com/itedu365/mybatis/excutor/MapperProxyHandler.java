package com.itedu365.mybatis.excutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.itedu365.mybatis.sqlsession.SqlSession;

public class MapperProxyHandler implements InvocationHandler {
	private SqlSession mySqlsession;

	public MapperProxyHandler(SqlSession mySqqlsession) {
		this.mySqlsession = mySqqlsession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		String mapper = method.getDeclaringClass().getName().replaceAll("\\.", "/") + ".xml";
		SqlMapParser.readSqlMapXML(mapper);
		return mySqlsession.selectOne(method.getName(), String.valueOf(args[0]));
	}
}
