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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 翻页标签类。
 * <p>
 * <Strong>使用说明</Strong>
 * <ul>
 * <Strong>用户初期登录时，根据个性设置在session里设定</Strong><br>
 * <li>extendPageSize前后成组翻页数（可选择5或者10，默认为5）；
 * <li>pageSize一页件数大小（默认为20）；<br>
 * <Strong>业务逻辑处理时，Service中设定</Strong>
 * <li>从Request请求里面取得startIndex的值；
 * <li>从Session里面取得pageSize一次最大取多少个数据；
 * <li>查询数据库，根据查询结果设置totalRecordNo查询个数；
 * <li>查询数据库，根据查询结果设置dataList查询数据对象；<br>
 * <Strong>画面显示时，在pageLink标签里设定</Strong>
 * <li>设定action名称；
 * <li>设定namespace名称；
 * <li>以及method名称；<br>
 * <Strong>国际化对应时，在属性资源文件里面定义好各国文字即可。</Strong>
 * </ul>
 * <p>
 * @since V1.0
 * @version 版本1.0 2013.10.20
 * @author 颜廷吉
 *         <p>
 * @see PageLinkTagBean
 * @see PageLinkTagService
 * @see PageLinkTagServiceSupport
 */
public class PageLinkTag extends ComponentTagSupport {

    /**
     * 版本序列号
     */
    private static final long serialVersionUID = 6309354407871711100L;

    /**
     * 行动响应(action)
     */
    private String action;

    /**
     * 方法(method)
     */
    private String method;

    /**
     * 一页内件数大小（默认20件）
     */
    private Integer pageSize;

    /**
     * 命名空间(namespace)
     */
    private String namespace;

    /**
     * 得到标签执行类。
     * @param valueStack ValueStack
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ApplicationMessageTagService
     */
    @Override
    public Component getBean(ValueStack valueStack, HttpServletRequest request, HttpServletResponse response) {
        return new PageLinkTagService(valueStack);
    }

    /**
     * 设定参数。
     */
    @Override
    protected void populateParams() {
        super.populateParams();
        PageLinkTagService pagingLinkTagService = (PageLinkTagService) getComponent();
        pagingLinkTagService.setAction(action);
        pagingLinkTagService.setMethod(method);
        pagingLinkTagService.setNamespace(namespace);
        pagingLinkTagService.setPageSize(pageSize);
    }

    /**
     * 取得行动响应(action)。
     * @return 行动响应(action)
     */
    public String getAction() {
        return action;
    }

    /**
     * 设定行动响应(action)。
     * @param action 行动响应(action)
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 取得方法(method)。
     * @return 方法(method)
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设定方法(method)。
     * @param method 方法(method)
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 取得命名空间(namespace)。
     * @return 命名空间(namespace)
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * 设定命名空间(namespace)。
     * @param namespace 命名空间(namespace)
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
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
