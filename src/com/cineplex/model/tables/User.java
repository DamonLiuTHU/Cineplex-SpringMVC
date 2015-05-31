package com.cineplex.model.tables;

import java.sql.Date;

public class User {
	private String phone;
	private String password;
	private int VIP;
	private Date expiration;
	private int ID;
	private int balance;
	private int age;
	private String gender;
	private String city;
	private String credit;
	
	
	public User(String phone, String password, int vIP, Date expiration,
			int iD, int balance, int age, String gender, String city,
			String credit) {
		super();
		this.phone = phone;
		this.password = password;
		VIP = vIP;
		this.expiration = expiration;
		ID = iD;
		this.balance = balance;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.credit = credit;
	}
	/**
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}
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
	public int getVIP() {
		return VIP;
	}
	public void setVIP(int vIP) {
		VIP = vIP;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public User(String phone, String password, int vIP, Date expiration, int iD,
			int balance, int age, String gender, String city) {
		super();
		this.phone = phone;
		this.password = password;
		VIP = vIP;
		this.expiration = expiration;
		ID = iD;
		this.balance = balance;
		this.age = age;
		this.gender = gender;
		this.city = city;
	}
	@Override
	public String toString() {
		return "User [phone=" + phone + ", password=" + password + ", VIP="
				+ VIP + ", expiration=" + expiration + ", ID=" + ID
				+ ", balance=" + balance + ", age=" + age + ", gender="
				+ gender + ", city=" + city + "]";
	}
	public User(String phone, int age, String gender, String city) {
		super();
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.city = city;
	}
	public User() {
		super();
	}
	
	
}
