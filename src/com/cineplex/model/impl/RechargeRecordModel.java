package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.cineplex.model.tables.RechargeRecord;
import com.cineplex.model.tables.User;

public class RechargeRecordModel {
	public static void addRecord(RechargeRecord rr){
		String sql = "insert into rechargerecord (phone,date,money) values (?,?,?)";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,rr.getPhone());
			ps.setString(2,rr.getDate());
			ps.setString(3,rr.getCost());
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	public static LinkedList<RechargeRecord> getRecharges(User u) {
		// TODO Auto-generated method stub
		LinkedList<RechargeRecord> rechargelist = new LinkedList<RechargeRecord>();
		String sql = "select * from rechargerecord where phone=?";
		Connection con = DBTools.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			System.out.println(u.getPhone());
			ps.setString(1,u.getPhone());
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String date = rs.getString("date");
				String money = rs.getString("money");
				RechargeRecord rr = new RechargeRecord(u.getPhone(), date, money);
				rechargelist.add(rr);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return rechargelist;
	}
}
