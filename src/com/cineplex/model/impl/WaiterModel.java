package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

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
	
	@SuppressWarnings("deprecation")
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
}
