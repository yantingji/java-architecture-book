package com.itedu.dto;

import java.time.LocalDate;

public class User {

	private Integer id;
	private String username;
	private String password;
	private String blockStatus;
	private Integer loginWrongTimes;
	private String stopStatus;
	private LocalDate passwordExpireDay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public Integer getLoginWrongTimes() {
		return loginWrongTimes;
	}

	public void setLoginWrongTimes(Integer loginWrongTimes) {
		this.loginWrongTimes = loginWrongTimes;
	}

	public String getStopStatus() {
		return stopStatus;
	}

	public void setStopStatus(String stopStatus) {
		this.stopStatus = stopStatus;
	}

	public LocalDate getPasswordExpireDay() {
		return passwordExpireDay;
	}

	public void setPasswordExpireDay(LocalDate passwordExpireDay) {
		this.passwordExpireDay = passwordExpireDay;
	}
	
}
