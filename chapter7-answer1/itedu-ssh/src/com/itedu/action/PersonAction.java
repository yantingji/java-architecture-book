package com.itedu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itedu.service.PersonService;

public class PersonAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 如何得到Spring容器实例
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(
				this.getServlet().getServletContext());
		PersonService personService = (PersonService) ctx.getBean("personService");
		request.setAttribute("persons", personService.getPersons());
		return mapping.findForward("list");
	}
	
}
