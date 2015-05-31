package com.cineplex.model.tables;

public class RechargeRecord {
	private String phone;
	private String date;
	private String money;
	public RechargeRecord(String phone, String date, String cost) {
		super();
		this.phone = phone;
		this.date = date;
		this.money = cost;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCost() {
		return money;
	}
	public void setCost(String cost) {
		this.money = cost;
	}
}
