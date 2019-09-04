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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性文件里取得属性工具类。
 * <p>
 * 详细请参考一下方法 * getPropertyNames() getPropertiesValues()
 * </p>
 * @since V1.0
 * @version 版本1.0 2013.10.20
 * @author 颜廷吉
 */
public class PropertyUtil {

    /**
     * 日志类
     */
    private static Log log = LogFactory.getLog(PropertyUtil.class);

    /**
     * 默认的属性文件名
     */
    public static final String DEFAULT_PROPERTY_FILE = "applicationConfig.properties";

    /**
     * 属性文件扩展名
     */
    private static final String PROPERTY_EXTENSION = ".properties";

    /**
     * 属性文件.properties长度
     */
    private static final Integer PROPERTY_LENTH_11 = 11;

    /**
     * 字系统名称长度(sys.)
     */
    private static final Integer SUB_SYSTEM_LENTH_5 = 5;

    /**
     * 文件名称长度
     */
    private static final Integer FILE_NAME_LENTH_16 = 16;

    /**
     * 属性文件内键值与键保存容器
     */
    private static TreeMap<String, String> props = new TreeMap<String, String>();

    /**
     * 类加载时，初期化属性文件。
     */
    static {
        load();
        getPropertyFiles();
    }

    /**
     * 读入指定的属性文件以及内容。
     */
    @SuppressWarnings("rawtypes")
    public static void load() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPERTY_FILE);
        try {
            try {
                Properties p = new Properties();
                p.load(is);
                // 读入属性文件内容
                for (Map.Entry e : p.entrySet()) {
                    // 读入的内容全部追加在props里面
                    props.put((String) e.getKey(), (String) e.getValue());
                }

            } catch (NullPointerException npe) {
                log.warn("*** Can not find property-file [" + DEFAULT_PROPERTY_FILE + ".properties] ***", npe);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 取得属性文件列表。<br>
     */
    @SuppressWarnings("rawtypes")
    public static void getPropertyFiles() {
        String resourceURL = PropertyUtil.class.getResource("/").toString();
        String resourcePath = resourceURL.substring(SUB_SYSTEM_LENTH_5, resourceURL.length()) + "i18nProperties/";
        // 新建文件实例
        File f = new File(resourcePath);
        // 此处获取文件夹下的所有文件
        File[] list = f.listFiles();
        for (int i = 0; i < list.length; i++) {
            String fileName = list[i].getName();
            if((fileName.length() - PROPERTY_LENTH_11) > 0){
                if (".properties".equals(fileName.substring(fileName.length() - PROPERTY_LENTH_11, fileName.length()))) {
                    try {
                        String i18nFilelanguage = getPropertyI18nLanguageName(fileName);
                        if (i18nFilelanguage != null) {
                            BufferedInputStream bis;
                            bis = new BufferedInputStream(new FileInputStream(list[i]));
                            // i18n属性读入
                            Properties p = new Properties();
                            p.load(bis);
                            for (Map.Entry e : p.entrySet()) {
                                // 读入的内容全部追加在props里面
                                props.put(i18nFilelanguage + "." + (String) e.getKey(), (String) e.getValue());
                            }
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 取得国际对应语言处理。
     * @param fileName 文件名称
     * @return 语言名称
     */
    public static String getPropertyI18nLanguageName(String fileName) {
        String i18nLanguageName = null;
        if (fileName.length() < FILE_NAME_LENTH_16) {
            return null;
        }

        i18nLanguageName = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.length() - PROPERTY_LENTH_11);
        if (StringUtil.isNotEmptStr(i18nLanguageName)) {
            i18nLanguageName = i18nLanguageName.replace("_", "-");
        }
        return i18nLanguageName;
    }

    /**
     * 根据属性名，取得属性文件对象。
     * @param propertyName 属性文件名称
     * @return 属性文件对象
     */
    public static Properties loadProperties(String propertyName) {
        if (propertyName == null || "".equals(propertyName)) {
            return null;
        }
        Properties retProps = new Properties();

        StringBuilder resourceName = new StringBuilder();
        resourceName.append(propertyName);
        if (!propertyName.endsWith(PROPERTY_EXTENSION)) {
            resourceName.append(PROPERTY_EXTENSION);
        }

        // 利用当前线程上下文加载器
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName.toString());
        if (is == null) {
            is = PropertyUtil.class.getResourceAsStream("/" + propertyName + PROPERTY_EXTENSION);
        }

        try {
            retProps.load(is);
        } catch (NullPointerException npe) {
            log.warn("*** Can not find property-file [" + propertyName + ".properties] ***", npe);
            retProps = null;
        } catch (IOException ie) {
            log.error("", ie);
            retProps = null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ie) {
                log.error("", ie);
                retProps = null;
            }
        }
        return retProps;
    }

    /**
     * 根据键值取得属性值。
     * <p>
     * </p>
     * @param key 键值
     * @return 值
     */
    public static String getProperty(String key) {
        String result = props.get(key);
        return result;
    }

    /**
     * 根据键值，取得属性值。
     * @param key 属性文件
     * @param defaultValue 默认的属性值
     * @return 键值
     */
    public static String getProperty(String key, String defaultValue) {
        String result = props.get(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 取得所有属性键列表。
     * @return 属性键列表
     */
    @SuppressWarnings("rawtypes")
    public static Enumeration getPropertyNames() {
        return Collections.enumeration(props.keySet());
    }

    /**
     * 取得以前缀为开始的属性键列表。
     * @param keyPrefix 键前缀
     * @return 属性键列表
     */
    public static Enumeration<String> getPropertyNames(String keyPrefix) {
        Map<String, String> map = props.tailMap(keyPrefix);
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            if (!name.startsWith(keyPrefix)) {
                return Collections.enumeration(props.subMap(keyPrefix, name).keySet());
            }
        }
        return Collections.enumeration(map.keySet());
    }

    /**
     * 根据属性文件名，部分键取得值集合。
     * @param propertyName 属性文件名
     * @param keyPrefix 部分键
     * @return 值集合
     */
    @SuppressWarnings("rawtypes")
    public static Set getPropertiesValues(String propertyName, String keyPrefix) {

        Properties localProps = loadProperties(propertyName);
        if (localProps == null) {
            return null;
        }

        Enumeration<String> keyEnum = getPropertyNames(localProps, keyPrefix);
        if (keyEnum == null) {
            return null;
        }

        return getPropertiesValues(localProps, keyEnum);
    }

    /**
     * 根据属性键值以及参数，取得内容。
     * @param propertyCode 键值
     * @param paras 参数
     * @return 内容
     */
    public static String getPropertiesValue(String propertyCode, String[] paras) {
        String tempValue = PropertyUtil.getProperty(propertyCode);
        if (paras != null) {
            int maxSize = paras.length;
            for (int i = 0; i < maxSize; i++) {
                String target = "{" + i + "}";
                String replacement = paras[i];
                tempValue = tempValue.replace(target, replacement);
            }
        }
        return tempValue;
    }

    /**
     * 根据属性文件名，和部分键前缀一致的键列表取得。
     * @param localProps 属性文件名
     * @param keyPrefix 部分键前缀
     * @return 键列表
     */
    public static Enumeration<String> getPropertyNames(Properties localProps, String keyPrefix) {

        if (localProps == null || keyPrefix == null) {
            return null;
        }

        Collection<String> matchedNames = new ArrayList<String>();
        Enumeration<?> propNames = localProps.propertyNames();
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            if (name.startsWith(keyPrefix)) {
                matchedNames.add(name);
            }
        }
        return Collections.enumeration(matchedNames);
    }

    /**
     * 根据属性文件名，以及键列表，取得属性值。
     * @param localProps 属性文件
     * @param propertyNames 键列表
     * @return 属性值集合
     */
    public static Set<String> getPropertiesValues(Properties localProps, Enumeration<String> propertyNames) {

        if (localProps == null || propertyNames == null) {
            return null;
        }

        Set<String> retSet = new HashSet<String>();
        while (propertyNames.hasMoreElements()) {
            retSet.add(localProps.getProperty(propertyNames.nextElement()));
        }
        return retSet;
    }
}
