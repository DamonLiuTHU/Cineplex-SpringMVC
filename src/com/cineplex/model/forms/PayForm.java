package com.cineplex.model.forms;

public class PayForm {
	private String account;
	private String password;
	private String phone;
	private int money;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "PayForm [account=" + account + ", password=" + password
				+ ", phone=" + phone + "]";
	}
	public PayForm(String account, String password, String phone, int money) {
		super();
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.money = money;
	}
	public PayForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
