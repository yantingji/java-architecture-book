
package com.itedu.chapter4.answer6.filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OutputSessionInfoFilter implements Filter {

	private static final String GET = "get";
	private static final String REQUEST_LOG = "_Request.log";
	private static final String RESPONSE_LOG = "_Response.log";

	private static final String SPACE_4 = "    ";

	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String servletPath = request.getServletPath().replaceAll("/", "_");
		String actionExtention = getActionExtention(request);
		if (actionExtention != null) {
			servletPath = servletPath.replaceAll(actionExtention, "");
		}
		String fileName = getCurrentTime() + servletPath;

		HttpSession session = request.getSession();

		makeSessionInfoFile(fileName + REQUEST_LOG, session);

		filterchain.doFilter(servletRequest, servletResponse);

		makeSessionInfoFile(fileName + RESPONSE_LOG, session);

	}

	public void destroy() {
	}

	private static void makeSessionInfoFile(String fileName, HttpSession session) {

		String userFilePath = "C:\\temp";

		File file = new File(userFilePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		File sessionFile = new File(userFilePath + "/" + fileName);

		FileOutputStream out;
		try {
			out = new FileOutputStream(sessionFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(writer);

			Enumeration<?> attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				String attributeName = (String) attributeNames.nextElement();

				StringBuffer strBuffer = new StringBuffer();

				getSessionContent(attributeName, session.getAttribute(attributeName), strBuffer, 0);
				bw.write(strBuffer.toString());
				bw.newLine();
			}

			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void getSessionContent(String attributeName, Object attributeContent, StringBuffer strBuffer,
			int deepNum) {
		deepNum++;
		if (attributeContent instanceof String) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent + getLineSeparator());
		} else if (attributeContent instanceof Integer) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof BigDecimal) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof Double) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof Float) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof Date) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof Boolean) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + attributeContent.toString()
					+ getLineSeparator());
		} else if (attributeContent instanceof Byte) {
			strBuffer.append(getSpaceLength(deepNum) + attributeContent.toString() + getLineSeparator());
		} else if (attributeContent instanceof Map) {
			getMapContent(attributeName, (Map<?, ?>) attributeContent, strBuffer, deepNum);
		} else if (attributeContent instanceof Hashtable) {
			getMapContent(attributeName, (Hashtable<?, ?>) attributeContent, strBuffer, deepNum);
		} else if (attributeContent instanceof ArrayList) {
			getListContent(attributeName, (ArrayList<?>) attributeContent, strBuffer, deepNum);
		} else if (attributeContent instanceof Vector) {
			getListContent(attributeName, (List<?>) attributeContent, strBuffer, deepNum);
		} else if (attributeContent != null) {
			getBeanContents(attributeContent, strBuffer, deepNum);
		}
	}

	private static void getMapContent(String attributeName, Map<?, ?> attributeContent, StringBuffer strBuffer,
			int deepNum) {
		for (Iterator<?> it = attributeContent.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4 + key + SPACE_4);
			getSessionContent(key, attributeContent.get(key), strBuffer, deepNum);
		}
	}

	private static void getListContent(String attributeName, List<?> attributeContent, StringBuffer strBuffer,
			int deepNum) {

		if (attributeContent == null) {
			return;
		}
		for (int i = 0; i < attributeContent.size(); i++) {
			strBuffer.append(getSpaceLength(deepNum) + attributeName + SPACE_4);
			String tmpAttributeName = "";
			if (attributeContent.get(i) != null) {
				tmpAttributeName = attributeContent.get(i).toString();
			}
			getSessionContent(tmpAttributeName, attributeContent.get(i), strBuffer, deepNum);
		}

	}

	private static void getBeanContents(Object objectClass, StringBuffer strBuffer, int deepNum) {
		Class<?> clazz;
		String className = objectClass.getClass().getName();
		Object beanObject = objectClass;
		try {
			clazz = Class.forName(className);
			Method[] method = clazz.getDeclaredMethods();

			for (int i = 0; i < method.length; i++) {
				try {
					method[i].setAccessible(true);
					String methodName = method[i].getName();
					Object object = null;
					if (methodName != null && GET.equals(methodName.substring(0, GET.length()))) {
						object = method[i].invoke(beanObject, new Object[] {});
					}
					getSessionContent(methodName, object, strBuffer, deepNum);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String getSpaceLength(int deepNum) {
		String tmpStr = "";
		for (int i = 0; i < deepNum; i++) {
			tmpStr = tmpStr + SPACE_4;
		}
		return tmpStr;

	}

	private static String getCurrentTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());

		return time;
	}

	public static String getActionExtention(HttpServletRequest request) {
		String actionExtention = (String) request.getAttribute("javax.servlet.forward.servlet_path");
		if (actionExtention != null) {
			actionExtention = actionExtention.substring(actionExtention.lastIndexOf('.'), actionExtention.length());
			if (actionExtention.indexOf("?") > 0) {
				actionExtention = actionExtention.substring(0, actionExtention.indexOf("?"));
			}
		}
		if (actionExtention == null) {
			actionExtention = ".do";
		}
		return actionExtention;
	}

	public static String getLineSeparator() {

		final String lineSeparator = System.getProperty("line.separator");
		return lineSeparator;
	}

}
