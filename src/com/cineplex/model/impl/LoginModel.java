package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cineplex.model.encrype.MD5;

public class LoginModel {
	public boolean isPwValid(String phone, String pw) {

		Connection con = DBTools.getConnection();
		try {
			pw = MD5.getMD5(pw.getBytes());
			PreparedStatement stmt = con
					.prepareStatement("select * from user where phone=? and password=?");
			stmt.setString(1, phone);
			stmt.setString(2, pw);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			boolean isvalid = rs.first();
			con.close();
			return isvalid;
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

}
