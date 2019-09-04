package com.itedu.chapter8.answer1.controller;

import com.itedu.chapter8.answer1.validator.IsMobile;

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
