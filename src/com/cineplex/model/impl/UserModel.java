package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.cineplex.model.tables.Movie;
import com.cineplex.model.tables.User;

public class UserModel {
	public static String getID(String phone) {
		Connection con = DBTools.getConnection();
		String sql = "select ID from user where phone=?";
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, phone);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				String result = rs.getString(1);
				con.close();
				return result;
			}
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

		return null;
	}

	public static boolean isVIP(String phone) {
		Connection con = DBTools.getConnection();
		String sql = "select * from user where phone=? and VIP=1";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			boolean is_VIP = rs.first();
			con.close();
			return is_VIP;
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

	public static void extendExpiration(String phone) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Connection con = DBTools.getConnection();
		String sql;
		PreparedStatement ps;
		sql = "select expiration from user where phone=?"; // 还要根据用户的会员状态（是否过期）来修改过期时间。
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			Date today = new Date();
			while (rs.next()) {
				String date_in_db = rs.getString(1);
				Date expirationdate = (Date) format.parse(date_in_db);
				if (expirationdate.before(today)) {
					expirationdate = today;
				}
				Calendar new_date = Calendar.getInstance();
				new_date.setTime(expirationdate);
				new_date.add(Calendar.YEAR, 1);
				int year = new_date.get(Calendar.YEAR);
				int month = new_date.get(Calendar.MONTH);
				month++;
				int day = new_date.get(Calendar.DAY_OF_MONTH);

				String date_to_store = year + "-" + month + "-" + day;

				String temp_sql = "update user set expiration=? where phone=?";
				ps = con.prepareStatement(temp_sql);
				ps.setString(1, date_to_store);
				ps.setString(2, phone);
				ps.execute();
				System.out.println("更新过期日期：" + date_to_store);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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
	}

	public static void setVIP(String phone) {
		Connection con = DBTools.getConnection();
		String sql = "update user set VIP=1 where phone=?"; // VIP-0：表示没激活会员 -1
															// ： 激活了会员
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
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

		// 修改会员过期时间
		extendExpiration(phone);

	}

	public static String getExpirationDate(String phone) {
		String sql = "select expiration from user where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String date = rs.getString(1);
				return date;
			}
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

		return null;
	}

	public static boolean isVIPExpired(String phone) {

		String sql = "select null from user where VIP=1 and phone=?";
		Connection con = DBTools.getConnection();
		PreparedStatement stmt;
		try {

			stmt = con.prepareStatement(sql);
			stmt.setString(1, phone);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			boolean is_VIP = rs.first();
			con.close();
			if (!is_VIP)
				return false;
			else {
				if (checkExpiration(phone)) {
					return true;
				} else {
					return false;
				}
			}

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

	/*
	 * 如果过期日期 早于 当前天的日期，则返回true。
	 */
	private static boolean checkExpiration(String phone) {
		String sql = "select expiration from user where phone=?";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, phone);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String date_in_db = rs.getString(1);
				Date expirationdate = (Date) format.parse(date_in_db);
				if (expirationdate.before(new Date())) { // 判断语句
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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

	/*
	 * 在会员过期的时候，设置会员状态为暂停，此时VIP字段设置为 -1.
	 */
	public static void pauseVIP(String phone) {
		String sql = "update user set VIP=-1 where phone=" + phone;
		Connection con = DBTools.getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static double getBalance(String phone) {
		// TODO Auto-generated method stub
		String sql = "select balance from user where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String balance = rs.getString(1);
				double balance_int = Double.parseDouble(balance);
				return balance_int;
			}
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
		return 0;
	}

	public static boolean isVIPPaused(String phone) {
		String sql = "select VIP from user where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String VIP = rs.getString(1);
				if (VIP.compareTo("-1") == 0)
					return true;
			}
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

	/*
	 * 在用户的会员身份被暂停之后（-1）使用，用于提示用户还有多久其身份会被停止（0）。其他情况禁止使用。
	 */
	public static int getDaysBeforeStopVIP(String phone) {

		return -1;
	}
	
	public static void addBalance(String phone,int money){
		double balance = getBalance(phone);
		balance += money;
		String sql = "update user set balance=? where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, phone);
			ps.execute();
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
	}
	
	public static synchronized void reduceBalance(String phone,double money) {
		double balance = getBalance(phone);
		balance -= money;
		String sql = "update user set balance=? where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, phone);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static User getUserById(String id){
		Session s = ModelManager.sharedInstance().getSession();
		Criteria criteria = s.createCriteria(User.class);
		Criterion criterion = Restrictions.eq("id", id);
		User u = (User) criteria.uniqueResult();
		return u;
	}
	
	public static User getUser(String phone){
		String sql = "select * from user where phone=?";
		Connection con = DBTools.getConnection();
		
		try {
			PreparedStatement ps =con.prepareStatement(sql);
			ps.setString(1,phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String credit = rs.getString("credit");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				String city = rs.getString("city");
				int VIP = rs.getInt("VIP");
				int ID = rs.getInt("ID");
				double balance = rs.getDouble("balance");
				int age = rs.getInt("age");
				java.sql.Date expiration  = (java.sql.Date) rs.getObject("expiration");
				User u = new User(phone, password, VIP, expiration, ID, balance, age, gender, city,credit);
				ps.close();
				con.close();
				return u;
			}
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
		return null;
	}
	
	public static ArrayList<User> getAllUser(){
		String sql = "select * from user";
		Connection con = DBTools.getConnection();
		ArrayList<User> result = new ArrayList<User>();
		
		try {
			Statement st = con.createStatement();
			st.execute(sql);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
				String phone = rs.getString("phone");
				String credit = rs.getString("credit");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				String city = rs.getString("city");
				int VIP = rs.getInt("VIP");
				int ID = rs.getInt("ID");
				int balance = rs.getInt("balance");
				int age = rs.getInt("age");
				java.sql.Date expiration  = (java.sql.Date) rs.getObject("expiration");
				User u = new User(phone, password, VIP, expiration, ID, balance, age, gender, city,credit);
				result.add(u);
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
		return null;
	}

	public static void updateUser(User u) {
		// TODO Auto-generated method stub
		String phone = u.getPhone();
		int age = u.getAge();
		String city = u.getCity();
		String gender = u.getGender();
//		System.out.println(city);
//		System.out.println(gender);
//		System.out.println(gender.);
		String sql = "update user set age=?,gender=?,city=? where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,age);
			ps.setString(2, gender);
			ps.setString(3,city);
			ps.setString(4,phone);
			ps.execute();
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
		
	}
	
	public static int getCredit(User u){
		String phone = u.getPhone();
		int result = -1;
		String sql = "select credit from user where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,phone);
			ps.execute();
			ResultSet rs =ps.getResultSet(); 
			while(rs.next()){
				result = rs.getInt("credit");
			}
			rs.close();
			ps.close();
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
	
	public static void setCredit(int credit,User u){
		String sql = "update user set credit=? where phone=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,credit);
			ps.setString(2,u.getPhone());
			ps.execute();
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
	}
	
	/*
	 * 对外提供的接口。
	 */
	public static void addCreidt(User u,int delta){
		int credit  = getCredit(u);
		credit += delta;
		setCredit(credit,u);
		
	}
	
	public static ArrayList<Movie> getMyMovies(String phone){
		if(phone == null){
			return new ArrayList<Movie>();
		}
		String sql = "select distinct movie.name,movie.id from movie,hall,orders where orders.phone=? and orders.periodID=hall.Id and hall.movieId=movie.Id";
		Connection con = DBTools.getConnection();
		ArrayList<Movie> result = new ArrayList<Movie>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String moviename = rs.getString("movie.name");
				String movieId = rs.getString("movie.id");
				Movie m = new Movie(moviename, movieId);
				result.add(m);
			}
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
	
	public static int getExpirationDaysLeft(String phone){
		String sql = "select datediff(now(),expiration) as days from user where phone=?";
		Connection con = DBTools.getConnection();
		int result = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				result = rs.getInt("days");
				break;
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
