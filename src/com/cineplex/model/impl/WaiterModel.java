package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cineplex.model.forms.QuestionnaireForm;

public class WaiterModel {
	
	
	
	public static boolean isPswValid(String id,String psw){
		String sql = "select * from waiter where waiterId=? and waiterPsw=?";
//		PreparedStatement ps = DBTools.getPreStmt(sql);
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,psw);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			return rs.first();
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
		return false;
	}
	
	public static ArrayList<QuestionnaireForm> getMyQuestions(String waiterId){
		ArrayList<QuestionnaireForm> result = new ArrayList<QuestionnaireForm>();
		String sql = "select * from questionnaireform where waiterId=? and state='on'";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, waiterId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				Date duedate = rs.getDate("duedate");
				java.util.Date today = new java.util.Date();
				
				if(today.after(duedate)){
					String description = rs.getString("description");
					String questionId = rs.getString("id");
					QuestionnaireForm qf = new QuestionnaireForm();
					qf.setDescription(description);
					qf.setId(Integer.parseInt(questionId));
					result.add(qf);
				}
			}
			con.close();
			return result;
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
		
		return result;
	}
	
	
	/*
	 * 统计一个服务员的计划通过率。即：通过的计划/他提交的所有计划
	 */
	public static double getWaiterPlanSuccessRate(String waiterId){
		double result = 0.0;
		int total = 0;
		int accepted = 0;
//		Session session = ModelManager.sharedInstance().getSession();
//		Criteria criteria = session.createCriteria(Plan.class);
		Connection con = DBTools.getConnection();
		try {
			String sql = "select count(*) as total from plans where waiterId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, waiterId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				total = rs.getInt("total");
			}
			
			sql = "select count(*) as total from plans where waiterId=? and state='ACCEPT'";
			ps = con.prepareStatement(sql);
			ps.setString(1, waiterId);
			ps.execute();
			rs = ps.getResultSet();
			while(rs.next()){
				accepted = rs.getInt("total");
			}
			
			rs.close();
			ps.close();
			con.close();
			
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
		
		if(total!=0){
			result = (double)accepted/(double)total;
		}
		
		return result;
	}
	
	public static ArrayList<String> getAllWaiters(){
		ArrayList<String> result = new ArrayList<String>();
		Connection con = DBTools.getConnection();
		String id = null;
		try {
			String sql = "select waiterId from waiter";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				id = rs.getString("waiterId");
				result.add(id);
			}
			rs.close();
			ps.close();
			con.close();
			
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
		return result;
	}
}
