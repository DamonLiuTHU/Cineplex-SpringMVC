package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanModel {

	public static boolean savePlan(String movieId, String starttime,
			String endtime, String waiterId, String hallId) {
		
		String sql = "insert into plans(movieId,start,end,waiterId,hallId) values (?,?,?,?,?)";
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,movieId);
			ps.setString(2,starttime);
			ps.setString(3,endtime);
			ps.setString(4,waiterId);
			ps.setString(5, hallId);;
			boolean saveresult = ps.execute();
			return saveresult;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
