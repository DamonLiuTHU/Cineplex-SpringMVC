package com.cineplex.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cineplex.model.impl.DBTools;




public class QuestionnaireController {
	
//	public static ArrayList<QuestionnaireForm> getQuestions(String movieId){
//		ArrayList<QuestionnaireForm> formlist = new ArrayList<QuestionnaireForm>();
//		
//		
//		
//		
//		return formlist;
//	}
	public static boolean isQuestionClosed(String qId){
		String sql = "select state from questionnaireform where id=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,qId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String state = rs.getString("state");
				if(state.compareTo("on")==0){
					return false;
				}else{
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
}
