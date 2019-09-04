package com.itedu365.struts2.framework.action;

import java.util.LinkedList;

import ognl.Ognl;
import ognl.OgnlContext;

public class ValueStack {
	private LinkedList<Object> root = new LinkedList<Object>();
	private OgnlContext context = new OgnlContext();

	public void push(Object obj) {
		root.push(obj);
	}

	public Object findValue(String ognl) {
		try {
			Object value = null;
			for (Object rootObj : root) {
				value = Ognl.getValue(ognl, rootObj);
				if (value != null && !"".equals(value)) {
					break;
				}
			}
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	public void setValue(String ognlExpression, Object value) {
		try {
			Object rootObj = root.peek();
			Ognl.setValue(ognlExpression, context, rootObj, value);
			System.out.println(Ognl.getValue(ognlExpression, rootObj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
