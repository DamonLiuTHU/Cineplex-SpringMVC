package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterModel {
	public boolean isIDExist(String id){
		Connection con = DBTools.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from user where phone="+id;
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			boolean exist = rs.first();
			con.close();
			return exist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public void saveRegisterInfo(String phone,String pw){
		Connection con = DBTools.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "insert into user (phone,password) values ("+phone+","+pw+")";
			stmt.execute(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
