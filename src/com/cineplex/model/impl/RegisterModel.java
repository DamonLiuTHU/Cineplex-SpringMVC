package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;

import com.cineplex.model.encrype.MD5;
import com.cineplex.tools.MailFactory;
import com.cineplex.tools.MailSender;
import com.cineplex.tools.MailSenderType;

public class RegisterModel {
	public boolean isIDExist(String id){
		Connection con = DBTools.getConnection();
		try {

			String sql = "select * from user where phone=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
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
		PreparedStatement pst = null;
		try {
			int code = (int) (Math.random()*9000+1000);
//			Statement stmt = con.createStatement();
			String sql = "insert into user(phone,password,activatecode) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, phone);
			String md5 = MD5.getMD5(pw.getBytes());
			pst.setString(2, md5);
			pst.setString(3, code+"");
			boolean success = pst.execute();
//			if(success){
				System.out.println("Sending Activate Code ==========================");
				MailSender sender = MailFactory.getSender(MailSenderType.SERVICE);
				try {
					sender.send(phone, code+"");
					System.out.println("Send Activate Code Success======================");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static boolean isCodeValid(String phone,String code){
		Connection con = DBTools.getConnection();
		try{
			String sql = "select null from user where phone=? and activatecode=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, phone);
			pst.setString(2, code);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			return rs.first();
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
