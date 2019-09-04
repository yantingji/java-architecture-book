package com.itedu.chapter4.answer6.controller;

import com.itedu.chapter4.answer6.validator.IsMobile;

public class UserForm {
	private String userName;
	@IsMobile
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
