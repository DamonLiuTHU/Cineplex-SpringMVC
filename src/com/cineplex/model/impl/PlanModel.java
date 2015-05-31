package com.cineplex.model.impl;

import java.sql.PreparedStatement;

public class PlanModel {

	public static boolean savePlan(String movieId, String starttime,
			String endtime, String waiterId, String hallId) {
		
		String sql = "insert into plans(movieId,start,end,waiterId,hallId) values (?,?,?,?,?)";
		PreparedStatement ps = DBTools.getPreStmt(sql);
		try{
			ps.setString(1,movieId);
			ps.setString(2,starttime);
			ps.setString(3,endtime);
			ps.setString(4,waiterId);
			ps.setString(5, hallId);;
			boolean saveresult = ps.execute();
			return saveresult;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
}
