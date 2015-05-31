package com.cineplex.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.cineplex.model.forms.PayForm;
import com.cineplex.model.impl.RechargeRecordModel;
import com.cineplex.model.tables.RechargeRecord;
import com.cineplex.model.tables.User;

public class RechargeController {
	public static void addRecord(PayForm pf){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time = sdf.format(now);
		RechargeRecord rr = new RechargeRecord(pf.getPhone(), time, pf.getMoney()+"");
		RechargeRecordModel.addRecord(rr);
	}

	public static LinkedList<RechargeRecord> getRecharges(User user) {
		// TODO Auto-generated method stub
		return RechargeRecordModel.getRecharges(user);
	}
}
