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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件操作工具类。
 * @since V1.0
 * @author 颜廷吉
 */
public class StringUtil {
    /**
     * 十六进制编码
     */
    private static final int SIXTEEN_0X00FF = 0x00ff;

    /**
     * 根据运行环境的OS取得换行符
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * 把字符串第一个字母转换成大写。
     * @param str 变换前字符串
     * @return 变换后字符串
     */
    public static String capitalizeInitial(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    /**
     * 判断是否是英文半角空格。
     * @param c 对象文字
     * @return 是的话返回true
     */
    public static boolean isWhitespace(char c) {
        return c == ' ';
    }

    /**
     * 字符串右侧的英文空格删除。 字符串是null的情况，返回null
     * <p>
     * 例如：Oracle里面，用ResultSet.getString()取得的CHAR型值的时候， 定义多长，就取得取得多长的字符串，不够的长度在右侧补足空格。<br>
     * 另外，VARCHAR2的时候，数据库会把右侧的空格剪切掉。同样的数据， 如果两者比较的话，结果会是不同的数据。 当然还有很多其他的地方可以利用此方法。
     * </p>
     * @param str 变换前的字符串
     * @return 变换后的字符串
     */
    public static String rtrim(String str) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        while ((0 < length) && isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        if (length < str.length()) {
            return str.substring(0, length);
        }
        return str;
    }

    /**
     * 字符串左侧的英文空格删除。
     * @param str 变换前的字符串
     * @return 变换后的字符串
     */
    public static String ltrim(String str) {
        if (str == null) {
            return null;
        }

        int start = 0;
        int length = str.length();
        while ((start < length) && isWhitespace(str.charAt(start))) {
            start++;
        }
        if (start > 0) {
            return str.substring(start, length);
        }
        return str;
    }

    /**
     * 字符串左右两侧的英文空格删除。
     * @param str 变换前的字符串
     * @return 变换后的字符串
     */
    public static String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 取得缩短的类名称（去掉类所在的包名称）。
     * @param longClassName 类名称全称
     * @return 缩短的类名称
     */
    public static String toShortClassName(String longClassName) {
        return ClassUtils.getShortClassName(longClassName);
    }

    /**
     * 把字符数组转换成16进制字符串。
     * @param byteArray 字符数组
     * @param delim 各个转换后16进制文字之间的分隔符（默认情况下是空）
     * @return 16进制字符串
     */
    public static String toHexString(byte[] byteArray, String delim) {
        if (delim == null) {
            delim = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (i > 0) {
                sb.append(delim);
            }
            String hex = Integer.toHexString(byteArray[i] & SIXTEEN_0X00FF).toUpperCase();
            if (hex.length() < 2) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 取得文件的宽展类型文字。 没有扩张类型的情况下，返回空字符串。<br/>
     * name是null的情况下，返回null。
     * @param name 带有扩张名的字符串
     * @return 扩展类型
     */
    public static String getExtension(String name) {
        if (name == null) {
            return null;
        }
        int index = name.lastIndexOf('.');
        if (index < 0) {
            return "";
        }
        return name.substring(index);
    }

    /**
     * 取得字符串的字符长度。<br/>
     * 第二个参数字符编码指定的情况下，按照指定的字符编码变换， 没有指定的情况下，按照默认的字符编码转换。
     * @param value 对象字符串
     * @param encoding 编码类型
     * @return �字符长度
     * @throws UnsupportedEncodingException 指定字符编码后的异常
     */
    public static int getByteLength(String value, String encoding) throws UnsupportedEncodingException {
        if (value == null || "".equals(value)) {
            return 0;
        }

        byte[] bytes = null;
        if (encoding == null || "".equals(encoding)) {
            bytes = value.getBytes();
        } else {
            try {
                bytes = value.getBytes(encoding);
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }

        if (bytes == null) {
            return 0;
        }
        return bytes.length;
    }

    /**
     * CSV形式的字符串转换成数组。
     * <p>
     * 开头或者结尾是逗号的情况下，开始或者最后的元素都会转换成空字符串
     * </p>
     * <p>
     * 连续的逗号也会变换成空字符串/p>
     * <p>
     * csvString 是 null的情况，变换成0元素数组。
     * @param csvString CSV形式的字符串
     * @return 用逗号分开的字符串数组
     */
    public static String[] parseCSV(String csvString) {
        if (csvString == null) {
            return new String[0];
        }
        if ("".equals(csvString)) {
            return new String[] { csvString };
        }

        Collection<String> result = new ArrayList<String>();

        char[] chars = csvString.toCharArray();
        int prevCommaIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',') {
                if (i == prevCommaIndex + 1) {
                    result.add("");
                } else {
                    result.add(new String(chars, prevCommaIndex + 1, i - (prevCommaIndex + 1)));
                }
                if (i == chars.length - 1) {
                    result.add("");
                }
                prevCommaIndex = i;
            } else if (i == chars.length - 1) {
                result.add(new String(chars, prevCommaIndex + 1, i - (prevCommaIndex + 1) + 1));
            }
        }

        return result.toArray(new String[0]);
    }

    /**
     * HTML标签字符串变换。
     * <p>
     * 把在HTML里输出有问题的 "&lt;"，"&gt;"，"&amp;"，"&quot;"文字变换成 "&amp;lt;"，"&amp;gt;"，"&amp;amp;"，"&amp;quot;"
     * </p>
     * <p>
     * null的场合返回null。
     * @param str 变换前字符串
     * @return 变换后字符串
     */
    public static String htmlFilter(String str) {
        if (str == null) {
            return null;
        }
        char[] cbuf = str.toCharArray();
        StringBuilder sbui = new StringBuilder();
        for (int i = 0; i < cbuf.length; i++) {
            if (cbuf[i] == '&') {
                sbui.append("&amp;");
            } else if (cbuf[i] == '<') {
                sbui.append("&lt;");
            } else if (cbuf[i] == '>') {
                sbui.append("&gt;");
            } else if (cbuf[i] == '"') {
                sbui.append("&quot;");
            } else {
                sbui.append(cbuf[i]);
            }
        }
        return sbui.toString();
    }

    /**
     * 非空字符串判断。
     * <p>
     * NULL或者""的时候返回false，否则返回true
     * </p>
     * @param str 字符串
     * @return 判断后的结果
     */
    public static boolean isNotEmptStr(String str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 空字符串判断。
     * <p>
     * NULL或者""的时候返回true否则返回false
     * </p>
     * @param str 字符串
     * @return 判断后的结果
     */
    public static boolean isNullOrEmpt(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * NULL转成空字符串处理。
     * <p>
     * NULL的时候返回""，否则返回原字符串
     * </p>
     * @param str 字符串
     * @return 如果是NULL，返回空字符串，如果不是，返回原数据。
     */
    public static String changeNullToStr(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }

    /**
     * 取得图形文件类型。
     * @param str 文件名称字符串
     * @return 返回文件类型。
     */
    public static String getImageFileType(String imageContentType) {
        String imageType = "";
        Integer slashPosation = imageContentType.indexOf("/");
        imageType = imageContentType.substring(slashPosation + 1, imageContentType.length());
        return imageType;
    }

    /**
     * 把数组内容转换成字符串。
     * @param str 数组
     * @return 字符串。
     */
    public static String changArrayToString(String[] strArray) {
        String str = "";
        if (strArray != null && strArray.length > 0) {
            for (int i = 0; i < strArray.length; i++) {
                str = str + "'" + strArray[i] + "',";
            }
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 把数组内容转换成字符串。
     * @param str 数组
     * @return 字符串。
     */
    public static String[] changStringToArrayStr(String strData) {
        String[] str = null;
        if (StringUtil.isNotEmptStr(strData)) {
            strData = strData.replaceAll(" ", "");
            str = strData.split(",");
        }
        return str;
    }

}
