package com.cineplex.model.tables;

public class Order {
	private String orderId;
	private String phone;
	private String seatId;
	private String periodId;
	private String mname;
	private String starttime;
	private String endtime;
	private String hallId;
	private String date;
	private String cost;
	
	public Order(String orderId, String phone, String seatId, String periodId,
			String mname, String starttime, String endtime, String hallId,
			String date) {
		super();
		this.orderId = orderId;
		this.phone = phone;
		this.seatId = seatId;
		this.periodId = periodId;
		this.mname = mname;
		this.starttime = starttime;
		this.endtime = endtime;
		this.hallId = hallId;
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Order(String orderId, String phone, String seatId, String periodId,
			String mname, String starttime, String endtime, String hallId) {
		super();
		this.orderId = orderId;
		this.phone = phone;
		this.seatId = seatId;
		this.periodId = periodId;
		this.mname = mname;
		this.starttime = starttime;
		this.endtime = endtime;
		this.hallId = hallId;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public Order(String orderId, String phone, String seatId, String periodId) {
		super();
		this.orderId = orderId;
		this.phone = phone;
		this.seatId = seatId;
		this.periodId = periodId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderId, String phone, String seatId, String periodId,
			String mname, String starttime, String endtime) {
		super();
		this.orderId = orderId;
		this.phone = phone;
		this.seatId = seatId;
		this.periodId = periodId;
		this.mname = mname;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Order(String orderId, String phone, String seatId, String periodId,
			String mname, String starttime, String endtime, String hallId,
			String date, String cost) {
		super();
		this.orderId = orderId;
		this.phone = phone;
		this.seatId = seatId;
		this.periodId = periodId;
		this.mname = mname;
		this.starttime = starttime;
		this.endtime = endtime;
		this.hallId = hallId;
		this.date = date;
		this.cost = cost;
	}
	
}
