/*
 * Copyright (C) 2013～2023 上海颐凡软件科技有限公司
 * Yfann Software Technology (Shanghai) Co.,LTD  
 * All Rights Reserved.
 * 公司网址: www.yfann.com
 * 365IT教育网，成就您高品质的国际软件架构师之梦！
 * 平台网址:
 *         www.365itedu.com
 */

package com.itedu.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.StrutsRequestWrapper;

/**
 * Request工具类。
 * @since V1.0
 * @author 颜廷吉
 */
public class RequestUtil {

    /**
     * <code>ServletRequest</code> 根据类实例，上下文环境以后的URL。<code>pathInfo</code>
     * @param request ServletRequest
     * @return 不含上下文环境，斜杠开始的路径。Request的参数是null的情况下，返回值是null。<br>
     *         上下文环境以后没指定的情况下，返回值是空字符串。
     */
    public static String getPathInfo(ServletRequest request) {
        if (request == null) {
            return null;
        }
        return ((HttpServletRequest) request).getRequestURI().replaceFirst(
                ((HttpServletRequest) request).getContextPath(), "");
    }

    /**
     * <code>ServletRequest</code> 根据类实例，全路径的URL。<code>pathInfo</code>
     * @param request ServletRequest
     * @return 全路径UTL。如：http://www.baidu.do
     */
    public static String getFullPathInfo(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String contextpath = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath
                + "/";
    }

    /**
     * 取得系统action路径，到扩展名为止。 如：http://www.baidu.do
     * @param request 请求信息
     * @return 系统atcion扩展名
     */
    public static String getActionPathInfo(ServletRequest request) {
        if (request == null) {
            return null;
        }
        String extendion = getActionExtention((HttpServletRequest) request);

        String tempPath = ((HttpServletRequest) request).getRequestURI().replaceFirst(
                ((HttpServletRequest) request).getContextPath(), "");
        if (tempPath.indexOf(extendion) > 0) {
            tempPath = tempPath.substring(0, tempPath.indexOf(extendion) + extendion.length());
        }
        return tempPath;
    }

    /**
     * 取得系统action扩展名，默认为.do。
     * @param request 请求信息
     * @return 系统atcion扩展名
     */
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

    /**
     * Servlet上下文环境取得。
     * @param request 请求信息
     * @return Servlet上下文环境
     */
    public static ServletContext getServletContext(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return request.getSession(true).getServletContext();
    }

    /**
     * 取得用户语言类型处理。
     * @param request 请求信息
     * @return String 语音种别
     */
    public static String getUserI18LanguageName(StrutsRequestWrapper request) {
//        String i18LanguageName = "";
//        String acceptLangage = (String) request.getHeader("Accept-Language");
//        String[] languageArray = acceptLangage.split(",");
//        for (String language : languageArray) {
//            if (language.contains("-")) {
//                language=language.substring(0, language.indexOf("-")).toLowerCase()+"-"+(language.substring(language.indexOf("-")+1,language.length())).toUpperCase() ;
//                i18LanguageName = language;
//                break;
//            } else if (StringUtil.isNotEmptStr(language)) {
//                i18LanguageName = language.substring(0, 2);
//                break;
//            }
//        }
//        return i18LanguageName;
        return "zh-CN";
    }

    /**
     * 取得用户语言类型处理。
     * @param request 请求信息
     * @return String 语音种别
     */
    @SuppressWarnings("unchecked")
    public static String getUserI18LanguageName(HttpServletRequest request) {
//        String i18LanguageName = "";
//        String acceptLangage = (String) request.getHeader("Accept-Language");
//        String[] languageArray = acceptLangage.split(",");
//        for (String language : languageArray) {
//            if (language.contains("-")) {
//                i18LanguageName = language;
//                break;
//            } else if (StringUtil.isNotEmptStr(language)) {
//                i18LanguageName = language.substring(0, 2);
//                break;
//            }
//
//        }
//        // 如果用户选择的国际语言没有对应，默认为中国大陆
//        Set<String> i18nLanguageNames = (Set<String>) request.getSession().getServletContext()
//                .getAttribute(FrameworkConst.I18N_LANGUAGE_NAMES_KEY);
//        if (!i18nLanguageNames.contains(i18LanguageName)) {
//            i18LanguageName = "zh-CN";
//        }
//        return i18LanguageName;
        return "zh-CN";
    }
}
