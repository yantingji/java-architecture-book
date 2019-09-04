package com.itedu.form;

public class UserForm {

	 private int id;

	    private String name;

	    private String  systemStatus;

	    private String password;

	    private int age;

	    public String getSystemStatus() {
			return systemStatus;
		}

		public void setSystemStatus(String systemStatus) {
			this.systemStatus = systemStatus;
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public int getAge() {
	        return age;
	    }

	    public void setAge(int age) {
	        this.age = age;
	    }

}
