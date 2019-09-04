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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 标签工具类。
 * @since V1.0
 * @author 颜廷吉
 */
public class TagUtil {

    /**
     * 翻页标签中，取得页大小处理。
     * @return 翻页标签页大小
     */
    public static Integer getPropertyPageSize() {
        Integer pageSize = null;
        String pageSizeString = PropertyUtil.getProperty("pageSize");
        if (pageSizeString != null && !"".equals(pageSizeString.trim())) {
            pageSize = Integer.valueOf(pageSizeString);
        }
        return pageSize;
    }

    /**
     * 翻页标签中，取得最大页大小处理。
     * @return 翻页标签页大小
     */
    public static Integer getPropertyMaxPageSize() {
        Integer pageMaxSizeInteger = null;
        String maxPageSizeString = PropertyUtil.getProperty("pageMaxSize");
        if (maxPageSizeString != null && !"".equals(maxPageSizeString.trim())) {
            pageMaxSizeInteger = Integer.valueOf(maxPageSizeString);
        }
        return pageMaxSizeInteger;
    }

    /**
     * 翻页标签中，取得组大小处理。
     * @return 组大小
     */
    public static Integer getPropertyExtendPageSize() {
        Integer pageExtendPageSize = null;
        String strExtendPageSize = PropertyUtil.getProperty("extendPageSize");
        if (strExtendPageSize != null && !"".equals(strExtendPageSize.trim())) {
            pageExtendPageSize = Integer.valueOf(strExtendPageSize);

        }
        return pageExtendPageSize;
    }

    /**
     * 翻页标签中，取得最大页数处理。
     * @return 组大小
     */
    public static Integer getPropertyMaxPageCount() {
        return getPropertyPageSize();
    }

    /**
     * 取得事件方法名称处理。
     * @param valueStack ValueStack
     * @return String 返回事件方法名称
     */
    @SuppressWarnings("unchecked")
    public static String getEventMethod(ValueStack valueStack) {
        String eventMethodName = "";
        Map<String, Object> parametersMap = (Map<String, Object>) valueStack.getContext().get("parameters");
        Iterator<Entry<String, Object>> it = parametersMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String entryKey = entry.getKey().toString();
            if (entryKey.contains("method:")) {
                eventMethodName = entryKey.substring("method:".length(), entryKey.length());
                break;
            }
        }
        if (eventMethodName.contains(".")) {
            eventMethodName = eventMethodName.substring(0, eventMethodName.indexOf("."));
        }
        return eventMethodName;
    }

    /**
     * 给消息参数赋值处理。
     * @param i18LanguageName 多国语言
     * @param message 消息内容
     * @param param 消息参数列表
     * @return String
     */
    public static String vonvertBracket(String i18LanguageName, String message, String[] param) {
        int i = 0;
        String strParam = "";
        if(message.contains("{}")){
            return message;
        }
        while (message != null && param != null && message.contains("{")) {
            strParam = PropertyUtil.getProperty(i18LanguageName + "." + param[i], param[i]);
            if (StringUtil.isNullOrEmpt(strParam)) {
                break;
            }
            if (StringUtil.isNotEmptStr(strParam)) {
                message = message.replace("{" + i + "}", strParam);
                i++;
            }
        }
        return message;
    }

}
