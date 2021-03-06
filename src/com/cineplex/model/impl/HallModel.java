package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cineplex.model.tables.Hall;
import com.cineplex.model.tables.Plan;

public class HallModel {

	public static double getPrice(String periodId) {

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
				// int price_int = Integer.parseInt(price);
				double price_double = Double.parseDouble(price);
				return price_double;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hallId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getMovieId());
			ps.setObject(2, p.getStart());
			ps.setObject(3, p.getEnd());
			ps.setInt(4, p.getHallId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Integer hallId = rs.getInt("hallId");
				halllist.add(hallId);
			}
			return halllist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static List<Hall> getHalls() {
		List<Hall> result = new ArrayList<>();

		String sql = "select * from hall"; // Id
		// 自增属性，不用管。
		// left_tickets
		// 为默认值51。
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String movieId = rs.getString("movieId");
				String start = rs.getString("start");
				String end = rs.getString("end");
				String hallId = rs.getString("hallId");
				String Id = rs.getString("Id");
				String left_tickets = rs.getString("left_tickets");
				Hall h = new Hall(Id, movieId, start, end, hallId, left_tickets);
				result.add(h);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static double[] getHallFinancialCondition(){
		double[] result= null;
		ArrayList<Integer> halls = getHallList();
		result = new double[halls.size()];
		for(int i = 0 ;i < halls.size() ;i++){
			int hallId = halls.get(i);
			result[i] = getHallFinancaialCondition(hallId);
		}
		return result;
	}
	
	private static double getHallFinancaialCondition(int hallId){
		String sql = "select sum(m.price) as result from orders o,hall h,movie m where h.hallId=? and o.periodId=h.Id and h.movieId=m.id";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hallId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				double result = rs.getDouble("result");
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0.0;
	}
}
