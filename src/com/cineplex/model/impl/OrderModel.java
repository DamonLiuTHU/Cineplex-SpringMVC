package com.cineplex.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import com.cineplex.model.tables.Order;

public class OrderModel {
	
	public static synchronized void makeOrder(String phone,String movieId,String seatId){
		String sql = "insert INTO orders (phone,periodId,seatId,date) values (?,?,?,?)";
//		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(today);
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, movieId);
			ps.setString(3, seatId);
			ps.setString(4, date);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized void waiterMakeOrder(String phone,String movieId,String seatId){
		String sql = "insert INTO orders (phone,periodId,seatId,date,type) values (?,?,?,?,?)";
//		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(today);
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, movieId);
			ps.setString(3, seatId);
			ps.setString(4, date);
			ps.setString(5,"cash");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static ArrayList<String> getUnavailableSeats(String periodId){
		String sql = "select seatId from orders where periodId=?";
		ArrayList<String> result = new ArrayList<String>();
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ps.setString(1, periodId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String seat = rs.getString("seatId");
				result.add(seat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static LinkedList<Order> getOrders(String phone){
		String sql = "select * FROM hall,movie,orders where orders.phone=? and orders.periodId=hall.Id and hall.movieId=movie.id";
		LinkedList<Order> result = new LinkedList<Order>();
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String date = rs.getString("date");
				String orderId = rs.getString("orderId");
				String seatId=rs.getString("seatId");
				String periodId=rs.getString("periodId");
				String name = rs.getString("name");
				String start = rs.getString("start");
				String end = rs.getString("end");
				String hallId = rs.getString("hallId");
				String price = rs.getString("price");
				Order o = new Order(orderId, phone, seatId, periodId, name, start, end,hallId,date,price);
				result.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
