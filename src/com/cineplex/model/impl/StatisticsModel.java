package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cineplex.model.tables.Statistics;
import com.cineplex.model.tables.User;

public class StatisticsModel {
	public static Statistics getStatistics(String month){
		ArrayList<User> userlist = UserModel.getAllUser();
		Statistics result = new Statistics(userlist);
		return result;
	}
	
	public static int[] getCashandOnline(){
		int[] result = new int[2];
		String sql = "select count(*) as number from orders where type=?";
		final String ONLINE = "online";
		final String CASH = "cash";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,ONLINE);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				result[0] = rs.getInt("number");
			}
			ps.setString(1,CASH);
			ps.execute();
			rs = ps.getResultSet();
			while(rs.next()){
				result[1] = rs.getInt("number");
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static double getMovieSeatRate(String movieId){
		String sql = "select 1-sum(left_tickets)/(count(*)*51) as rate from hall where movieId=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,movieId);
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				return rs.getDouble("rate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0.0;
	}
}
