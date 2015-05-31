package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BankModel {
	public static boolean isPasswordValid(String account,String password){
		Connection con = DBTools.getConnection();
		String sql = "select * from icbc where account=? and password=?";

		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			System.out.println(ps.toString());
			ps.execute();
			ResultSet rs = ps.getResultSet();
			boolean valid = rs.first();
			con.close();
			return valid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return false;
	}
}
