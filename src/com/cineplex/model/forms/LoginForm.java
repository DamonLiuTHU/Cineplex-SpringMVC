package com.cineplex.model.forms;

public class LoginForm {
	private String phone;
	private String password;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [phone=" + phone + ", password=" + password + "]";
	}
	
	
}
