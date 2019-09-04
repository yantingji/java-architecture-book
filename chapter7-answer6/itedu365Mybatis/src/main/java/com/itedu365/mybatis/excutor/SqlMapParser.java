package com.itedu365.mybatis.excutor;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SqlMapParser {
	private static ClassLoader loader = ClassLoader.getSystemClassLoader();
	private static List<?> sqlMap;

	public static void readSqlMapXML(String sqlmapXML) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream stream = loader.getResourceAsStream(sqlmapXML);
			Document document = saxReader.read(stream);
			Element root = document.getRootElement();
			sqlMap = root.elements();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static String getSqlContent(String sqlId) {
		String sqlContent = "";
		for (int i = 0; i < sqlMap.size(); i++) {
			Element select = (Element) sqlMap.get(i);
			if (sqlId.equals(select.attributeValue("id"))) {
				sqlContent = select.getText();
			}
		}
		return sqlContent;
	}

	public static String getSqlResultClass(String sqlId) {
		String sqlResultClass = "";
		for (int i = 0; i < sqlMap.size(); i++) {
			Element select = (Element) sqlMap.get(i);
			if (sqlId.equals(select.attributeValue("id"))) {
				sqlResultClass = select.attributeValue("resultType");
			}
		}
		return sqlResultClass;
	}
}
