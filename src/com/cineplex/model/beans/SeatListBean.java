package com.cineplex.model.beans;

import java.util.ArrayList;



public class SeatListBean {
	private ArrayList<String> seatlist = new ArrayList<String>();

	public ArrayList<String> getSeatlist() {
		return seatlist;
	}

	public void setSeatlist(ArrayList<String> seatlist) {
		this.seatlist = seatlist;
	}

	public SeatListBean(ArrayList<String> seatlist) {
		super();
		this.seatlist = seatlist;
	}
	
}
