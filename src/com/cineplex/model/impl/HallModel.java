package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cineplex.model.tables.Plan;
public class HallModel {

	public static int getPrice(String periodId) {
		
		java.sql.Connection con = null;
		String sql = "select movie.price from movie,hall where hall.Id=? and movie.id=hall.movieId";
		try {
			con = DBTools.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, periodId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String price = rs.getString(1);
				int price_int = Integer.parseInt(price);
				return price_int;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return -1;
	}

	private static void clearHall(String hallId) {
		String sql = "delete from hall where hallId=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.setString(1, hallId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updateHallArrange(ArrayList<Plan> planlist) {

		/*
		 * 不可以删除掉，上传的计划根据状态的变更，用于服务员自己查看自己上传的计划的状态。
		 */
		/*
		 * 最新update ： 可以删除，搞混了，并不是plans中的状态。
		 */
		clearHall(planlist.get(0).getHallId() + ""); // 将先前的删除掉

		// 一条一条插入
		for (Plan p : planlist) {
			insertPlan(p);
		}

	}

	private static void insertPlan(Plan p) {
		String sql = "insert into hall(movieId,start,end,hallId) values(?,?,?,?)"; // Id
																					// 自增属性，不用管。
																					// left_tickets
																					// 为默认值51。
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.setInt(1, p.getMovieId());
			ps.setObject(2, p.getStart());
			ps.setObject(3, p.getEnd());
			ps.setInt(4, p.getHallId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Integer> getHallList() {
		ArrayList<Integer> halllist = new ArrayList<Integer>(10);
		String sql = "select * from halllist"; // Id
												// 自增属性，不用管。
												// left_tickets
												// 为默认值51。
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				Integer hallId = rs.getInt("hallId");
				halllist.add(hallId);
			}
			return halllist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
