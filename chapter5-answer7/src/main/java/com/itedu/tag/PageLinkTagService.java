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

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import com.itedu.constants.FrameworkConstant;
import com.itedu.tag.PageLinkTagServiceSupport.NameEnum;
import com.itedu.utils.RequestUtil;
import com.itedu.utils.StringUtil;
import com.itedu.utils.TagUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 翻页标签处理类。
 * @since V1.0
 * @author 颜廷吉
 */
public class PageLinkTagService extends Component {

    /**
     * <a href=\"javascript:void(0);\" onclick=\"commitForm('
     */
    private static final String A_HREF_LEFT_START = "<a href=\"javascript:void(0);\" onclick=\"commitForm('";

    /**
     * ')\">
     */
    private static final String A_HREF_LEFT_END = "')\">";

    /**
     * </a>
     */
    private static final String A_HREF_RIHGT = "</a>";

    /**
     * 一个英文空格
     */
    private static final String ONE_SPACE = "&nbsp;";

    /**
     * 两个英文空格
     */
    private static final String TWO_SPACE = "&nbsp;&nbsp;";

    /**
     * 加粗标签开始<strong>
     */
    private static final String STRONG_LEFT = "<strong>";

    /**
     * 加粗标签开始结束</strong>
     */
    private static final String STRONG_RIGHT = "</strong>";

    /**
     * 默认一页内20件
     */
    private static final Integer PAG_ESIZE_DEFAULT_20 = 20;

    /**
     * 默认一页最大件数为1000件
     */
    private static final Integer MAX_PAGE_SIZE_DEFAULT_1000 = 1000;

    /**
     * 行动响应
     */
    private String action = "";

    /**
     * 方法
     */
    private String method = "";

    /**
     * 命名空间
     */
    private String namespace = "";

    /**
     * 要访问URL
     */
    private String forwordURL = "";

    /**
     * 数据检索开始索引
     */
    private Integer startIndex = 0;

    /**
     * 当前页开始件数
     */
    private Integer currentPageStartNo = 0;

    /**
     * 当前页最后件数
     */
    private Integer currentPageEndNo = 0;

    /**
     * 所有记录数
     */
    private Integer totalRecordNo = 0;

    /**
     * 当前页数
     */
    private Integer currentPageNo = 0;

    /**
     * 所有页数
     */
    private Integer totalPageNo = 0;

    /**
     * 一页内件数大小（默认20件）
     */
    private Integer pageSize = 0;

    /**
     * 前后成组翻页组大小（默认5件一组）
     */
    private Integer extendPageSize = FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_FIVE;

    /**
     * 一页最大件数（最大1000件）
     */
    private Integer maxPageSize = MAX_PAGE_SIZE_DEFAULT_1000;

    /**
     * StrutsRequestWrapper请求
     */
    private StrutsRequestWrapper request;

    /**
     * sessionMap
     */
    private Map<String, Object> sessionMap;

    /**
     * 中华 中国
     */
    private String i18LanguageName = "zh-CH";

    /**
     * 构造函数。
     * @param stack ValueStack
     */
    public PageLinkTagService(ValueStack stack) {
        super(stack);
        this.stack = stack;
    }

    /**
     * 标签逻辑处理。
     * @param writer Writer
     * @return boolean
     */
    @Override
    public boolean start(Writer writer) {
        boolean result = super.start(writer);
        try {
            StringBuilder strBuilder = new StringBuilder();
            request = (StrutsRequestWrapper) stack.getContext().get(StrutsStatics.HTTP_REQUEST);
            sessionMap = ActionContext.getContext().getSession();
            // 设定客户端语言
            i18LanguageName = RequestUtil.getUserI18LanguageName(request);

            // 设定一页件数大小
            setPageSize();

            // 设定翻页组大小
            setExtendPageSize();

            // 设定开始SQL索引
            setStartIndex();

            // 设定总件数
            setTotalRecordNo();

            // 设定当前页码以及总页码
            setCurrentAndTotalPageNo();

            // 设定当前页开始与结束案件位置数字
            setCurrentPageStartEndNo();

            // 生成请求URL
            makeForwordURL();

            // 生成Javascirpt提交表单代码
            makeJavascriptForCommitForm(strBuilder);

            // 生成链接显示内容
            makePagesContents(strBuilder);

            // 输出HTML结果
            writer.write(strBuilder.toString());

        } catch (IOException e) {
            throw new RuntimeException("Construct pageLinkTag error");
        }

        return result;
    }

    /**
     * 设定总件数。
     */
    private void setTotalRecordNo() {
        Integer tempTotalRecordNo = (Integer) stack.findValue(FrameworkConstant.TOTAL_RECORD_NO_KEY);
        if (tempTotalRecordNo != null) {
            totalRecordNo = tempTotalRecordNo;
        }
    }

    /**
     * 设定页大小。
     */
    private void setPageSize() {
        // 标签没设定一页内件数大小
        if (pageSize == null || pageSize == 0) {
            // 取得属性文件中一页值
            if (TagUtil.getPropertyPageSize() != null) {
                pageSize = TagUtil.getPropertyPageSize();
            }

            // 取得Session中一页值
            Integer pageSizeSession = (Integer) sessionMap.get(FrameworkConstant.PAGE_SIZE_KEY);
            if (pageSizeSession != null) {
                pageSize = pageSizeSession;
            }

            if (pageSize == null || pageSize == 0) {
                pageSize = PAG_ESIZE_DEFAULT_20;
            }
        }

        // 取得属性文件中一页最大值
        if (TagUtil.getPropertyMaxPageSize() != null) {
            maxPageSize = TagUtil.getPropertyMaxPageSize();
        }

        // 1到1000范围判断
        if (pageSize > maxPageSize) {
            pageSize = maxPageSize;
        }
    }

    /**
     * 设定数据检索开始索引大小。
     */
    private void setStartIndex() {
        String strStartIndex = (String) request.getParameter(FrameworkConstant.START_INDEX_KEY);
        if (strStartIndex != null) {
            startIndex = Integer.valueOf(strStartIndex);
        }

    }

    /**
     * 设定前后分组翻页大小。
     */
    private void setExtendPageSize() {
        // 取得属性文件中分组值
        Integer propertyExtendPageSize = TagUtil.getPropertyExtendPageSize();
        if (propertyExtendPageSize != null) {
            if (propertyExtendPageSize == FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_FIVE
                    || propertyExtendPageSize == FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_TEN) {
                extendPageSize = propertyExtendPageSize;
            }
        }

        // 取得Session中分组值
        String extendPageSizeSession = (String) sessionMap.get(FrameworkConstant.EXTEND_PAGE_SIZE_CD_KEY);
        if (StringUtil.isNotEmptStr(extendPageSizeSession)) {
            if (FrameworkConstant.SYSCD001_02.equals(extendPageSizeSession)) {
                extendPageSize = FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_TEN;

            } else {
                extendPageSize = FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_FIVE;
            }
        }
    }

    /**
     * 设定当前页开始结束数据件数大小。
     */
    private void setCurrentPageStartEndNo() {
        currentPageStartNo = startIndex + 1;

        if (currentPageStartNo > totalRecordNo) {
            currentPageStartNo = totalRecordNo;
        }

        if ((startIndex + pageSize) > totalRecordNo) {
            currentPageEndNo = totalRecordNo;
        } else {
            currentPageEndNo = currentPageStartNo + pageSize - 1;
        }
    }

    /**
     * 设定当前以及总页数大小。
     */
    private void setCurrentAndTotalPageNo() {

        currentPageNo = (startIndex + pageSize) / pageSize;
        totalPageNo = (totalRecordNo + pageSize - 1) / pageSize;

        if (currentPageNo > totalPageNo) {
            currentPageNo = totalPageNo;
        }
        if (currentPageNo < 0) {
            currentPageNo = 0;
        }
    }

    /**
     * 生成访问URL地址。
     */
    private void makeForwordURL() {
        String contextPath = request.getContextPath();
        forwordURL = contextPath + namespace + "/" + action + "!" + method + RequestUtil.getActionExtention(request);
    }

    /**
     * 生成页内容处理。
     * @param strBuilder StringBuilder
     */
    private void makePagesContents(StringBuilder strBuilder) {
        strBuilder.append("<div class='page_link'>");

        // 后翻页后，设置5页或者10页时，
        boolean showAfterExtend = false;
        boolean showBeforeExtend = false;
        showAfterExtend = judgeAfterExtend();
        showBeforeExtend = judgeBeforeExtend();

        // 当前页与总页数相等
        if (currentPageNo.equals(totalPageNo)) {
            // 如果totalPageNo <= 1，则无需分页，显示“[共M页 当前第Z页・共N件 当前第 X～Y件]”的基本信息
            if (currentPageNo <= 1) {
                // [共M页 当前第Z页・共N件 当前第 X～Y件]
                makeBasePageInfo(strBuilder);
            } else {
                // 到达最后一页,显示“[共M页 当前第Z页・共N件 当前第 X～Y件] [首页] [上十页] [上一页] | [末页]”
                // [共M页 当前第Z页・共N件 当前第 X～Y件]
                makeBasePageInfo(strBuilder);
                // [首页]
                makeFirstPageURL(strBuilder);
                // 超过10页，显示 [上十页]
                if (showBeforeExtend) {
                    makePreviousExtendSizePageURL(strBuilder);
                }
                // [上一页]
                makePreviousPageURL(strBuilder);
                // 前页码
                makeCurrentPageURL(strBuilder);
                // 最后一页
                makeLastPageURL(strBuilder);
            }
        } else {
            // 当前页与总页数不相同
            if (currentPageNo == 1) {
                // 第一页，显示“[共M页 当前第Z页・共N件 当前第 X～Y件] [首页] | [下一页] [下十页] [末页]”
                // [共M页 当前第Z页・共N件 当前第 X～Y件]
                makeBasePageInfo(strBuilder);
                // [首页]
                makeFirstPageURL(strBuilder);
                // 当前页码
                makeCurrentPageURL(strBuilder);
                // [下一页]
                makeNextPageURL(strBuilder);
                // 超过10页，显示 [下十页]
                if (showAfterExtend) {
                    makeNextExtendPageURL(strBuilder);
                }
                // [末页]
                makeLastPageURL(strBuilder);
            } else {
                // 不是第一页，显示“[共M页 当前第Z页・共N件 当前第 X～Y件] [首页] [上十页][上一页] | [下一页] [下十页] [末页]”
                // [共M页 当前第Z页・共N件 当前第 X～Y件]
                makeBasePageInfo(strBuilder);
                // [首页]
                makeFirstPageURL(strBuilder);
                // 超过10页，显示 [上十页]
                if (showBeforeExtend) {
                    makePreviousExtendSizePageURL(strBuilder);
                }
                // [上一页]
                makePreviousPageURL(strBuilder);
                // 当前页码
                makeCurrentPageURL(strBuilder);
                // [下一页]
                makeNextPageURL(strBuilder);
                // 超过10页，显示 | [下十页]
                if (showAfterExtend) {
                    makeNextExtendPageURL(strBuilder);
                }
                // 最后一页
                makeLastPageURL(strBuilder);
            }
        }
        strBuilder.append("</div>");
    }

    /**
     * 生成Javascript表单提交代码处理。
     * @param strBuilder StringBuilder
     */
    private void makeJavascriptForCommitForm(StringBuilder strBuilder) {
        strBuilder.append("<script language=\"javascript\" >");
        strBuilder.append("function commitForm(currentStartIndex) {");
        strBuilder.append("document.forms[1].action = '" + forwordURL + "?startIndex='" + "+ currentStartIndex" + ";");
        strBuilder.append("document.forms[1].method=\"post\";");
        strBuilder.append("document.forms[1].submit();}");
        strBuilder.append("</script>");
    }

    /**
     * 判断当前页是否大于组大小处理。
     * @return Boolean
     */
    private Boolean judgeBeforeExtend() {
        if (currentPageNo - extendPageSize >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前页到最后页的页码数是否大于组的页码数大小处理。
     * @return Boolean
     */
    private Boolean judgeAfterExtend() {
        if (totalPageNo - currentPageNo >= extendPageSize) {
            return true;
        }
        return false;
    }

    /**
     * 生成页基本信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeBasePageInfo(StringBuilder strBuilder) {
        // [共M页 当前第Z页・共N件 当前第X～Y件]
        strBuilder.append(getTagContentName(NameEnum.NAME_LEFT_BRACKET) + getTagContentName(NameEnum.NAME_SUM)
                + totalPageNo + getTagContentName(NameEnum.NAME_PAGE) + ONE_SPACE
                + getTagContentName(NameEnum.NAME_CURRENT_ITEM) + currentPageNo + getTagContentName(NameEnum.NAME_PAGE)
                + getTagContentName(NameEnum.NAME_DOT) + ONE_SPACE + getTagContentName(NameEnum.NAME_SUM)
                + totalRecordNo + getTagContentName(NameEnum.NAME_ITEM) + ONE_SPACE
                + getTagContentName(NameEnum.NAME_CURRENT_ITEM) + currentPageStartNo
                + getTagContentName(NameEnum.NAME_BETWEEN) + currentPageEndNo + getTagContentName(NameEnum.NAME_ITEM)
                + getTagContentName(NameEnum.NAME_RIGHT_BRACKET) + TWO_SPACE);
    }

    /**
     * 生成第一页信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeFirstPageURL(StringBuilder strBuilder) {
        strBuilder.append(A_HREF_LEFT_START + 0 + A_HREF_LEFT_END + getTagContentName(NameEnum.NAME_FIRST)
                + A_HREF_RIHGT + ONE_SPACE);
    }

    /**
     * 生成下一个组URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeNextExtendPageURL(StringBuilder strBuilder) {
        Integer previousStartIndex = startIndex + pageSize * extendPageSize;
        if (currentPageNo + extendPageSize > totalPageNo) {
            previousStartIndex = pageSize * totalPageNo - 1;
        }
        if (extendPageSize == FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_TEN) {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + previousStartIndex + A_HREF_LEFT_END
                    + getTagContentName(NameEnum.NAME_NEXT_EXTEND_TEN) + A_HREF_RIHGT + ONE_SPACE);
        } else {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + previousStartIndex + A_HREF_LEFT_END
                    + getTagContentName(NameEnum.NAME_NEXT_EXTEND_FIVE) + A_HREF_RIHGT + ONE_SPACE);
        }
    }

    /**
     * 生成下一页URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeNextPageURL(StringBuilder strBuilder) {
        Integer nextStartIndex = startIndex + pageSize;
        strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + nextStartIndex + A_HREF_LEFT_END
                + getTagContentName(NameEnum.NAME_NEXT) + A_HREF_RIHGT + ONE_SPACE);
    }

    /**
     * 生成当前页URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeCurrentPageURL(StringBuilder strBuilder) {
        if (getTagContentName(NameEnum.VERTICAL_LINE) == null || getTagContentName(NameEnum.VERTICAL_LINE).isEmpty()) {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + startIndex + A_HREF_LEFT_END + ONE_SPACE + STRONG_LEFT
                    + currentPageNo + STRONG_RIGHT + ONE_SPACE + A_HREF_RIHGT + ONE_SPACE);
        } else {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + startIndex + A_HREF_LEFT_END + ONE_SPACE + STRONG_LEFT
                    + getTagContentName(NameEnum.VERTICAL_LINE) + STRONG_RIGHT + ONE_SPACE + A_HREF_RIHGT + ONE_SPACE);
        }
    }

    /**
     * 生成前一页URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makePreviousPageURL(StringBuilder strBuilder) {
        Integer previousStartIndex = startIndex - pageSize;
        strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + previousStartIndex + A_HREF_LEFT_END
                + getTagContentName(NameEnum.NAME_PREVIOUS) + A_HREF_RIHGT + ONE_SPACE);
    }

    /**
     * 生成前一组页URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makePreviousExtendSizePageURL(StringBuilder strBuilder) {
        Integer previousStartIndex = startIndex - pageSize * extendPageSize;
        if (previousStartIndex < 0) {
            previousStartIndex = 0;
        }
        if (extendPageSize == FrameworkConstant.EXTEND_PAGE_SIZE_VALUE_TEN) {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + previousStartIndex + A_HREF_LEFT_END
                    + getTagContentName(NameEnum.NAME_PREVIOUS_EXTEND_TEN) + A_HREF_RIHGT + ONE_SPACE);
        } else {
            strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + previousStartIndex + A_HREF_LEFT_END
                    + getTagContentName(NameEnum.NAME_PREVIOUS_EXTEND_FIVE) + A_HREF_RIHGT + ONE_SPACE);
        }
    }

    /**
     * 生成最后一页URL信息处理。
     * @param strBuilder StringBuilder
     */
    private void makeLastPageURL(StringBuilder strBuilder) {
        Integer lastStartIndex = (totalPageNo - 1) * pageSize;
        strBuilder.append(ONE_SPACE + A_HREF_LEFT_START + lastStartIndex + A_HREF_LEFT_END
                + getTagContentName(NameEnum.NAME_LAST) + A_HREF_RIHGT);
    }

    /**
     * 取得标签内容名字。
     * @param nameEnum NameEnum
     * @return String 标签内容名字
     */
    private String getTagContentName(NameEnum nameEnum) {
        return PageLinkTagServiceSupport.getDisplayStyle(i18LanguageName, nameEnum);
    }

    /**
     * 取得命名空间。
     * @return String 命名空间
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * 设定命名空间。
     * @param namespace 命名空间
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * 取得方法。
     * @return String 方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设定方法。
     * @param method 方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 取得行为响应。
     * @return String 行为响应
     */
    public String getAction() {
        return action;
    }

    /**
     * 设定行为响应。
     * @param action 行为响应
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 取得一页内件数大小。
     * @return Integer 一页内件数大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设定一页内件数大小。
     * @param pageSize 一页内件数大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
