package com.cineplex.model.tables;

import java.util.ArrayList;

public class Statistics {
	ArrayList<User> userlist;

	/**
	 * @return the userlist
	 */
	public ArrayList<User> getUserlist() {
		return userlist;
	}

	/**
	 * @param userlist the userlist to set
	 */
	public void setUserlist(ArrayList<User> userlist) {
		this.userlist = userlist;
	}

	public Statistics(ArrayList<User> userlist) {
		super();
		this.userlist = userlist;
	}
	
}
