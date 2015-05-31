package com.cineplex.model.forms;

public class RegisterForm {
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
		return "RegisterForm [phone=" + phone + ", password=" + password + "]";
	}
	
}
