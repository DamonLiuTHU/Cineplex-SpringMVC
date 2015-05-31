package com.cineplex.model.forms;

public class ActivateForm {
	private String phone;
	private String account;
	private String bank;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Override
	public String toString() {
		return "ActivateForm [phone=" + phone + ", account=" + account
				+ ", bank=" + bank + "]";
	}
	
	
	
}
