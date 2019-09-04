package com.itedu365.mybatis.sqlsession;

public class SessionFactory {
	
	public static SqlSession getSessionInstance() {
		return new SqlSession();
	}

}
