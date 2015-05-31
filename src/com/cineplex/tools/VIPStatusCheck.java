package com.cineplex.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cineplex.model.impl.DBTools;

public class VIPStatusCheck {
	public static void checkVIP(){
		/*
		 * 分为两种情况处理 1：会员过期的，VIP状态设置为 -1.
		 * 				   2：会员状态已经为 -1，并切过期超过了一年的，状态设置为 0；
		 */
		checkAllExpiredVIP();
		removeVIP();
	}
	private static void checkAllExpiredVIP(){
		System.out.println("Check Expired VIP ===================================");
		String sql = "update user m set m.VIP=-1 where VIP=1 and expiration<NOW()";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void removeVIP(){
		System.out.println("Remove Expired VIP ====================================");
		String sql = "update user set VIP=0 where vip=-1 and datediff(now(),expiration)>365";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
