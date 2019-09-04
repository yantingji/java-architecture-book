package com.itedu365.struts2.framework.filter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.itedu365.struts2.framework.config.ActionConfig;
import com.itedu365.struts2.framework.config.ResultConfig;
import com.itedu365.struts2.framework.interceptor.base.Interceptor;

public class PrepareOperations {

	private Map<String, ActionConfig> actionConfigMap = new HashMap<String, ActionConfig>();

	private LinkedList<Interceptor> linkedList = new LinkedList<Interceptor>();

	@SuppressWarnings("unchecked")
	public static PrepareOperations getInstance(InputStream xml) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(xml);
			Element root = doc.getRootElement();
			PrepareOperations prepare = new PrepareOperations();
			// Action
			List<Element> actionElementList = root.elements("action");
			for (Element actionElement : actionElementList) {
				String actionName = actionElement.attribute("name").getValue();
				if ("_*".equals(actionName.substring(actionName.length() - 2, actionName.length()))) {
					actionName = actionName.substring(0, actionName.length() - 2);
				}
				String actionClazz = actionElement.attribute("class").getValue();
				ActionConfig actionConfig = new ActionConfig();
				actionConfig.setActionName(actionName);
				actionConfig.setClazz(actionClazz);

				List<Element> resultElementList = actionElement.elements("result");
				for (Element resultElement : resultElementList) {
					String resultName = resultElement.attribute("name").getValue();
					String resultType = resultElement.attribute("type").getValue();
					String resultValue = resultElement.getText().trim();
					ResultConfig resultCfg = new ResultConfig();
					resultCfg.setName(resultName);
					resultCfg.setValue(resultValue);
					resultCfg.setType(resultType);
					actionConfig.addResultConfig(resultCfg);
				}

				prepare.actionConfigMap.put(actionConfig.getActionName(), actionConfig);
			}

			// Interceptor
			List<Element> interceptorsElementList = root.elements("interceptors");
			for (Element interceptorsElement : interceptorsElementList) {
				List<Element> interceptorElementList = interceptorsElement.elements("interceptor");
				for (Element interceptorElement : interceptorElementList) {
					String clazz = interceptorElement.attribute("class").getValue();
					Class<?> classObject = Class.forName(clazz);
					Object obj = classObject.newInstance();
					if (obj instanceof Interceptor) {
						prepare.linkedList.add((Interceptor) obj);
					}
				}

			}
			return prepare;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ActionConfig getActionConfig(String name) {
		return actionConfigMap.get(name);
	}

	public LinkedList<Interceptor> getInterceptors() {
		return linkedList;
	}
}
