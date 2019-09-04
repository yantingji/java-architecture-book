package com.itedu.spring.framework.webmvc.servlet;

import com.itedu.spring.framework.annotation.Controller;
import com.itedu.spring.framework.annotation.RequestMapping;
import com.itedu.spring.framework.annotation.RequestParam;
import com.itedu.spring.framework.beans.BeanWrapper;
import com.itedu.spring.framework.context.WebApplicationContext;
import com.itedu.spring.framework.webmvc.HandlerAdapter;
import com.itedu.spring.framework.webmvc.HandlerMapping;
import com.itedu.spring.framework.webmvc.ModelAndView;
import com.itedu.spring.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

	private final String LOCATION = "contextConfigLocation";
	WebApplicationContext applicationContext = null;

	private List<HandlerMapping> handlerMappings = new ArrayList<>();
	private Map<HandlerMapping, HandlerAdapter> handlerAdapterMap = new HashMap<>();
	private List<ViewResolver> viewResolvers = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			doDispatch(req, resp);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		String strLocation = config.getInitParameter(LOCATION);
		applicationContext = new WebApplicationContext(strLocation);
		initStrategies(applicationContext);

	}

	private void initStrategies(WebApplicationContext context) {
		initHandlerMappings(context);// 通过HandlerMapping，将请求映射到处理器
		initHandlerAdapters(context);// 通过HandlerAdapter进行多类型的参数动态匹配
		initViewResolvers(context);// 通过viewResolver解析逻辑视图到具体视图实现
	}

	private void initHandlerMappings(WebApplicationContext context) {
		String[] beanNames = context.getBeanDefinitions();
		for (String beanName : beanNames) {
			BeanWrapper beanWrapper = (BeanWrapper) context.getBean(beanName);

			if (!beanWrapper.get_originalBean().getClass().isAnnotationPresent(Controller.class)) {
				continue;
			}
			Class<?> clazz = beanWrapper.get_originalBean().getClass();
			String strBaseUrl = "";
			if (clazz.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping classRM = clazz.getAnnotation(RequestMapping.class);
				strBaseUrl = classRM.value().trim();
			}

			// Controller扫描之后,接着扫描其Method
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (!method.isAnnotationPresent(RequestMapping.class)) {
					continue;
				}

				RequestMapping methodRM = method.getAnnotation(RequestMapping.class);
				String methodUrl = methodRM.value().trim();
				String strTotalUrl = ("/" + strBaseUrl + methodUrl.replaceAll("\\*", ".*")).replaceAll("/+", "/");
				this.handlerMappings
						.add(new HandlerMapping(beanWrapper.get_originalBean(), method, Pattern.compile(strTotalUrl)));
				System.out.println("Mapping: " + strTotalUrl + " , " + method);
			}

		}
	}

	private void initHandlerAdapters(WebApplicationContext context) {
		for (HandlerMapping handlerMapping : this.handlerMappings) {
			Method method = handlerMapping.getMethod();
			Map<String, Integer> methodParamMapping = new HashMap<>();

			Annotation[][] paras = method.getParameterAnnotations();

			// 先处理命名参数
			for (int i = 0; i < paras.length; i++) {
				for (Annotation a : paras[i]) {
					if (a instanceof RequestParam) {
						String paraName = ((RequestParam) a).value().trim();
						methodParamMapping.put(paraName, i);
						break;
					}
				}
			}

			// 再处理request, response参数
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				if (parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class) {
					// 记录下method形参中request,response两种参数的位置
					methodParamMapping.put(parameterTypes[i].getName(), i);
				}
			}

			this.handlerAdapterMap.put(handlerMapping, new HandlerAdapter(handlerMapping, methodParamMapping));
			System.out.println("initHandlerAdapters: " + methodParamMapping + " , " + method);
		}
	}

	private void initViewResolvers(WebApplicationContext context) {
		String fileRootPath = context.getConfig().getProperty("templateRoot");
		String absolutePaths = this.getClass().getClassLoader().getResource(fileRootPath).getFile();

		File viewDirectory = new File(absolutePaths);
		for (File file : viewDirectory.listFiles()) {
			this.viewResolvers.add(new ViewResolver(file.getName(), file));
		}
	}

	private void doDispatch(HttpServletRequest request, HttpServletResponse response)
			throws InstantiationException, IllegalAccessException {
		HandlerMapping handlerMapping = getHandler(request, response);
		if (handlerMapping == null) {
			try {
				response.getWriter().write("404 not found");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		HandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);
		ModelAndView viewAndModel = handlerAdapter.handler(request, response);
		processDispatcherResult(request, response, viewAndModel);
	}

	private void processDispatcherResult(HttpServletRequest request, HttpServletResponse response,
			ModelAndView viewAndModel) {

		try {
			if (viewAndModel == null) {
				response.getWriter().write("404 not found, please try again");
				return;
			}
			for (ViewResolver viewResolver : this.viewResolvers) {
				if (viewAndModel.getViewName().equals(viewResolver.getViewName())) {
					String strResult = viewResolver.processViews(viewAndModel);
					strResult = strResult == null ? "" : strResult;
					response.getWriter().write(strResult);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HandlerAdapter getHandlerAdapter(HandlerMapping handlerMapping) {
		if (handlerMapping == null) {
			return null;
		}
		return this.handlerAdapterMap.get(handlerMapping);
	}

	private HandlerMapping getHandler(HttpServletRequest request, HttpServletResponse response) {
		if (this.handlerMappings.size() < 1) {
			return null;
		}

		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		url = url.replace(contextPath, "").replace("/+", "/");
		for (HandlerMapping handlerMapping : this.handlerMappings) {
			if (handlerMapping.getUrlPattern().matcher(url).matches()) {
				return handlerMapping;
			}
		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

}
