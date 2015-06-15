package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import com.cineplex.model.forms.AnswerForm;
@Repository
public class AnswerModel {
	Configuration config;
	static SessionFactory sessionFactory;
	
	
	
	public AnswerModel() {
		super();
		// TODO Auto-generated constructor stub
		config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	public static void save(AnswerForm af){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(af);
		tx.commit();
		session.close();
	}
	
	public static boolean alreadyAnswered(String phone,String qId){
		String sql = "select null from AnswerForm where phone=? and qId=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,phone);
			ps.setString(2, qId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			boolean result = rs.first();
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
		
		return false;
	}
	public static String getMostSelection(String questionId) {
		// TODO Auto-generated method stub
		String sql = "select selection,count(*) from answerform where qId=? group by selection order by count(*) desc";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,questionId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String result = rs.getString("selection");
				rs.close();
				ps.close();
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
	public static ArrayList<AnswerForm> getAnswersForQuestion(String questionId) {
		// TODO Auto-generated method stub
		String sql = "select * from answerform where qId=?";
		ArrayList<AnswerForm> result = new ArrayList<AnswerForm>();
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,questionId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
				String phone = rs.getString("phone");
				String selection = rs.getString("selection");
				AnswerForm af = new AnswerForm();
				af.setSelection(selection);
				af.setPhone(phone);
				result.add(af);
			}
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
}
