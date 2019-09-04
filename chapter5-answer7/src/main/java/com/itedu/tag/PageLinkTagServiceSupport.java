/*
 * Copyright (C) 2013～2023 上海颐凡软件科技有限公司
 * Yfann Software Technology (Shanghai) Co.,LTD  
 * All Rights Reserved.
 * 公司网址: www.yfann.com
 * 365IT教育网，成就您高品质的国际软件架构师之梦！
 * 平台网址:
 *         www.365itedu.com
 */

package com.itedu.tag;

import com.itedu.utils.PropertyUtil;

/**
 * 翻页标签页面显示风格协助类。
 * @since V1.0
 * @author 颜廷吉
 */
public class PageLinkTagServiceSupport {

    /**
     * 默认翻页标签显示文字：[
     */
    private static String defaultLeftBracket = "[";

    /**
     * 默认翻页标签显示文字：]
     */
    private static String defaultRightBracket = "]";

    /**
     * 默认翻页标签显示文字：・
     */
    private static String defaultDot = "・";

    /**
     * 默认翻页标签显示文字：共
     */
    private static String defaultSum = "共";

    /**
     * 默认翻页标签显示文字：件
     */
    private static String defaultItem = "件";

    /**
     * 默认翻页标签显示文字：当前第
     */
    private static String defaultCurrentItem = "当前第";

    /**
     * 默认翻页标签显示文字：～
     */
    private static String defaultBetween = "～";

    /**
     * 默认翻页标签显示文字：页
     */
    private static String defaultPage = "页";

    /**
     * 默认翻页标签显示文字：[首页]
     */
    private static String defaultFirst = "[首页]";

    /**
     * 默认翻页标签显示文字：[下五页]
     */
    private static String defaultNextExtendFive = "[下五页]";

    /**
     * 默认翻页标签显示文字：[下十页]
     */
    private static String defaultNextExtendTen = "[下十页]";

    /**
     * 默认翻页标签显示文字：[下一页]
     */
    private static String defaultNext = "[下一页]";

    /**
     * 默认翻页标签显示文字：[上一页]
     */
    private static String defaultPrevious = "[上一页]";

    /**
     * 默认翻页标签显示文字：[上五页]
     */
    private static String defaultPreviousExtendFive = "[上五页]";

    /**
     * 默认翻页标签显示文字：[上十页]
     */
    private static String defaultPreviousExtendTen = "[上十页]";

    /**
     * 默认翻页标签显示文字：[末页]
     */
    private static String defaultLast = "[末页]";

    /**
     * 默认翻页标签显示文字：默认中间没有竖线
     */
    private static String defaultVerticalLine = "";

    /**
     * 命名结果
     */
    private static String nameResult = "";

    /**
     * 显示文字类型
     */
    enum NameEnum {

        /** [ */
        NAME_LEFT_BRACKET,

        /** ] */
        NAME_RIGHT_BRACKET,

        /** ・ */
        NAME_DOT,

        /** 共 */
        NAME_SUM,

        /** 件 */
        NAME_ITEM,

        /** 当前第 */
        NAME_CURRENT_ITEM,

        /** ～ */
        NAME_BETWEEN,

        /** 页 */
        NAME_PAGE,

        /** [首页] */
        NAME_FIRST,

        /** [下一页] */
        NAME_NEXT,

        /** [下五页] */
        NAME_NEXT_EXTEND_FIVE,

        /** [下十页] */
        NAME_NEXT_EXTEND_TEN,

        /** [上一页] */
        NAME_PREVIOUS,

        /** [上五页] */
        NAME_PREVIOUS_EXTEND_FIVE,

        /** [上十页] */
        NAME_PREVIOUS_EXTEND_TEN,

        /** [末页] */
        NAME_LAST,

        /** ｜ */
        VERTICAL_LINE;
    }

    /**
     * 取得页面翻页标签显示风格。
     * @param i18LanguageName String
     * @param nameEnum NameEnum
     * @return String 页面翻页标签显示风格
     */
    public static String getDisplayStyle(String i18LanguageName, NameEnum nameEnum) {
        // 取得国际化语言标签文字
        setCustomName(i18LanguageName);
        switch (nameEnum) {

        case NAME_LEFT_BRACKET:
            nameResult = defaultLeftBracket;
            break;

        case NAME_RIGHT_BRACKET:
            nameResult = defaultRightBracket;
            break;
        case NAME_DOT:
            nameResult = defaultDot;
            break;

        case NAME_SUM:
            nameResult = defaultSum;
            break;

        case NAME_ITEM:
            nameResult = defaultItem;
            break;
        case NAME_CURRENT_ITEM:
            nameResult = defaultCurrentItem;
            break;

        case NAME_BETWEEN:
            nameResult = defaultBetween;
            break;

        case NAME_PAGE:
            nameResult = defaultPage;
            break;

        case NAME_FIRST:
            nameResult = defaultFirst;
            break;

        case NAME_NEXT:
            nameResult = defaultNext;
            break;

        case NAME_NEXT_EXTEND_FIVE:
            nameResult = defaultNextExtendFive;
            break;

        case NAME_NEXT_EXTEND_TEN:
            nameResult = defaultNextExtendTen;
            break;

        case NAME_PREVIOUS:
            nameResult = defaultPrevious;
            break;

        case NAME_PREVIOUS_EXTEND_FIVE:
            nameResult = defaultPreviousExtendFive;
            break;

        case NAME_PREVIOUS_EXTEND_TEN:
            nameResult = defaultPreviousExtendTen;
            break;

        case NAME_LAST:
            nameResult = defaultLast;
            break;
        case VERTICAL_LINE:
            nameResult = defaultVerticalLine;
            break;
        default:
            break;

        }
        return nameResult;
    }

    /**
     * 取得本地化名字。
     * @param i18LanguageName String
     */
    private static void setCustomName(String i18LanguageName) {
        String customLeftBracket = "";
        String customRightBracket = "";
        String customDot = "";
        String customSum = "";
        String customItem = "";
        String customCurrentItem = "";
        String customBetween = "";
        String customPage = "";
        String customFirst = "";
        String customNext = "";
        String customNextExtendFive = "";
        String customNextExtendTen = "";
        String customPrevious = "";
        String customPreviousExtendFive = "";
        String customPreviousExtendTen = "";
        String customLast = "";
        String customVerticalLine = "";

        if ("zh-CN".equals(i18LanguageName)) {
            customLeftBracket = PropertyUtil.getProperty("theme.custom.left.bracket");
            customRightBracket = PropertyUtil.getProperty("theme.custom.right.bracket");
            customSum = PropertyUtil.getProperty("theme.custom.sum");
            customDot = PropertyUtil.getProperty("theme.custom.dot");
            customItem = PropertyUtil.getProperty("theme.custom.item");
            customCurrentItem = PropertyUtil.getProperty("theme.custom.current.item");
            customBetween = PropertyUtil.getProperty("theme.custom.between");
            customPage = PropertyUtil.getProperty("theme.custom.page");
            customFirst = PropertyUtil.getProperty("theme.custom.first");
            customNext = PropertyUtil.getProperty("theme.custom.next");
            customNextExtendFive = PropertyUtil.getProperty("theme.custom.next.extend.five");
            customNextExtendTen = PropertyUtil.getProperty("theme.custom.next.extend.ten");
            customPrevious = PropertyUtil.getProperty("theme.custom.previous");
            customPreviousExtendFive = PropertyUtil.getProperty("theme.custom.previous.extend.five");
            customPreviousExtendTen = PropertyUtil.getProperty("theme.custom.previous.extend.ten");
            customLast = PropertyUtil.getProperty("theme.custom.last");
            customVerticalLine = PropertyUtil.getProperty("theme.custom.verticalLine");
        } else {
            customLeftBracket = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.left.bracket");
            customRightBracket = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.right.bracket");
            customDot = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.dot");
            customSum = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.sum");
            customItem = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.item");
            customCurrentItem = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.current.item");
            customBetween = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.between");
            customPage = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.page");
            customFirst = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.first");
            customNext = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.next");
            customNextExtendFive = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.next.extend.five");
            customNextExtendTen = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.next.extend.ten");
            customPrevious = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.previous");
            customPreviousExtendFive = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.previous.extend.five");
            customPreviousExtendTen = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.previous.extend.ten");
            customLast = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.last");
            customVerticalLine = PropertyUtil.getProperty(i18LanguageName + ".theme.custom.verticalLine");
        }

        boolean isSetValue = true;
        if (customLeftBracket == null || "".equals(customLeftBracket)) {
            isSetValue = false;
        }
        if (customRightBracket == null || "".equals(customRightBracket)) {
            isSetValue = false;
        }
        if (customSum == null || "".equals(customSum)) {
            isSetValue = false;
        }
        if (customDot == null || "".equals(customDot)) {
            isSetValue = false;
        }
        if (customItem == null || "".equals(customItem)) {
            isSetValue = false;
        }
        if (customCurrentItem == null || "".equals(customCurrentItem)) {
            isSetValue = false;
        }
        if (customBetween == null || "".equals(customBetween)) {
            isSetValue = false;
        }
        if (customPage == null || "".equals(customPage)) {
            isSetValue = false;
        }
        if (customFirst == null || "".equals(customFirst)) {
            isSetValue = false;
        }
        if (customNext == null || "".equals(customNext)) {
            isSetValue = false;
        }
        if (customNextExtendFive == null || "".equals(customNextExtendFive)) {
            isSetValue = false;
        }
        if (customNextExtendTen == null || "".equals(customNextExtendTen)) {
            isSetValue = false;
        }
        if (customPrevious == null || "".equals(customPrevious)) {
            isSetValue = false;
        }
        if (customPreviousExtendFive == null || "".equals(customPreviousExtendFive)) {
            isSetValue = false;
        }
        if (customPreviousExtendTen == null || "".equals(customPreviousExtendTen)) {
            isSetValue = false;
        }
        if (customLast == null || "".equals(customLast)) {
            isSetValue = false;
        }

        if (isSetValue) {
            defaultLeftBracket = customLeftBracket;
            defaultRightBracket = customRightBracket;
            defaultSum = customSum;
            defaultDot = customDot;
            defaultItem = customItem;
            defaultCurrentItem = customCurrentItem;
            defaultBetween = customBetween;
            defaultPage = customPage;
            defaultFirst = customFirst;
            defaultNext = customNext;
            defaultNextExtendFive = customNextExtendFive;
            defaultNextExtendTen = customNextExtendTen;
            defaultPrevious = customPrevious;
            defaultPreviousExtendFive = customPreviousExtendFive;
            defaultPreviousExtendTen = customPreviousExtendTen;
            defaultLast = customLast;
            defaultVerticalLine = customVerticalLine;
        }
    }
}
