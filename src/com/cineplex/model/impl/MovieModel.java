package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.cineplex.model.tables.Hall;
import com.cineplex.model.tables.Movie;

public class MovieModel {
	
	static Configuration config = new Configuration().configure();;
	static ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
	
	static SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
	
	
	
	public MovieModel() {
		super();
		// TODO Auto-generated constructor stub
		config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	
	public static ArrayList<Movie> getMovies() {
		String sql = "select * from movie";
		ArrayList<Movie> list = new ArrayList<Movie>();
		try {
			Statement stmt = DBTools.getConnection().createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String name = rs.getString(1);
				String country = rs.getString(2);
				String company = rs.getString(3);
				String price = rs.getString(4);
				String long_description = rs.getString(5);
				String publish_date = rs.getString(6);
				String director = rs.getString(7);
				String main_star = rs.getString(8);
				String type = rs.getString(9);
				String short_description = rs.getString(10);
				String id = rs.getString("id");
				String poster = rs.getString(12);
				Movie m = new Movie(name, country, company, price,
						long_description, publish_date, director, main_star,
						type, short_description, id, poster);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static ArrayList<Hall> getArrangements(String movieId) {

		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式
		String now_time = df.format(new Date());
		String sql = "select * from hall where movieId=? and start>?";
		ArrayList<Hall> list = new ArrayList<Hall>();
		try {
			PreparedStatement ps = DBTools.getConnection()
					.prepareStatement(sql);
			ps.setString(1, movieId);
			ps.setString(2, now_time);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {

				String start = rs.getString("start");
				String end = rs.getString("end");
				String hallId = rs.getString("hallId");
				String Id = rs.getString("Id");
				String left_tickets = rs.getString("left_tickets");
				Hall item = new Hall(Id, movieId, start, end, hallId,
						left_tickets);
				list.add(item);
				// System.out.println(starttime+"-"+endtime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static String getPrice(String movieId) {
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select price from movie where id=?");
			stmt.setString(1, movieId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String price = rs.getString("price");
				con.close();
				return price;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// public static void main(String args[]){
	// MovieModel.getArrangements("1");
	// }

	public static int getLeftTickets(String id) {
		Connection con = DBTools.getConnection();

		try {
			PreparedStatement stmt = con
					.prepareStatement("select left_tickets from hall where Id=?");
			stmt.setString(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				int result = rs.getInt(1);
				con.close();
				return result;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static synchronized void reduceTicket(String id, int ticket_number) {
		// TODO Auto-generated method stub
		Connection con = DBTools.getConnection();
		int left_tickets = getLeftTickets(id);
		try {
			PreparedStatement stmt = con
					.prepareStatement("update hall set left_tickets=? where Id=?");
			stmt.setInt(1, left_tickets-ticket_number);
			stmt.setString(2, id);
			stmt.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void save(Movie m) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(m);
		tx.commit();
		session.close();
	}
}
