package com.itedu.ssi.struts2Action;

/**
 * @author hanzl
 * ���ã�����struts2������
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itedu.ssi.model.UserModel;
import com.itedu.ssi.service.TestService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TestStruts2Action extends ActionSupport implements Action {
	private static final long serialVersionUID = 1L;
	private TestService testService;
	private List<UserModel> list = new ArrayList<UserModel>();

	public String execute() {
		try {
			list = testService.queryforlist();
			for (UserModel u : list) {
				System.out.println(u.getId() + " " + u.getUsername());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}

	public List<UserModel> getList() {
		return list;
	}

	public void setList(List<UserModel> list) {
		this.list = list;
	}

	private UserModel u = new UserModel();

	public UserModel getU() {
		return u;
	}

	public void setU(UserModel u) {
		this.u = u;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
