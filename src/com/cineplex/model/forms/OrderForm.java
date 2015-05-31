package com.cineplex.model.forms;

import com.cineplex.model.beans.SeatListBean;

public class OrderForm {
	private String phone;
	private String Id;
	private SeatListBean seatlist;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public SeatListBean getSeatlist() {
		return seatlist;
	}

	public void setSeatlist(SeatListBean seatlist) {
		this.seatlist = seatlist;
	}

	public OrderForm(String phone, String id, SeatListBean seatlist) {
		super();
		this.phone = phone;
		Id = id;
		this.seatlist = seatlist;
	}
	
}
