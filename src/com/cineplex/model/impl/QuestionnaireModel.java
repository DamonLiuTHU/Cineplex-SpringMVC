package com.cineplex.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import com.cineplex.model.forms.AnswerForm;
import com.cineplex.model.forms.QuestionnaireForm;
import com.cineplex.model.tables.Questionnaire;
import com.cineplex.model.tables.User;
@Repository
public class QuestionnaireModel {

	Configuration config;
	static SessionFactory sessionFactory;
	
	
	
	public QuestionnaireModel() {
		super();
		// TODO Auto-generated constructor stub
		config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	public static void save(Questionnaire q){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(q);
		tx.commit();
		session.close();
	}
	public static void save(QuestionnaireForm q){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		java.util.Date today = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		q.setPublishdate(sdf.format(today));
		session.save(q);
		tx.commit();
		session.close();
	}
	public static List<QuestionnaireForm> getQuestions(String movieId){
		List<QuestionnaireForm> result = new ArrayList<QuestionnaireForm>();
//		Session session = sessionFactory.openSession();
////		String hql = "select * from "+QuestionnaireForm.class;
//		result = session.get(QuestionnaireForm.class, id);
		String sql = "select * from QuestionnaireForm where movieId=? and state='on'";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,movieId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()){
//				Object o = rs. 
				Date duedate = rs.getDate("duedate");
				java.util.Date nowdate = new java.util.Date();
				if(nowdate.before(duedate)){
					int id = rs.getInt("id");
					String credit = rs.getString("credit");
					String description = rs.getString("description");
					String publishdate = rs.getString("publishdate");
					String selection1 = rs.getString("selection1");
					String selection2 = rs.getString("selection2");
					String selection3 = rs.getString("selection3");
					QuestionnaireForm qf = new QuestionnaireForm(id, description, selection1, selection2, selection3, duedate.toString(), credit, publishdate, "", movieId);
					result.add(qf);
				}
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		
		
		return result;
	}
	/*
	 * 1.更改字段state为off
	 * 2.统计选择最多的一个选项并且予以加分。cretdit。
	 */
	public static void closeQuestino(String questionId) {
		// TODO Auto-generated method stub
		setQuestionOff(questionId);
		addCreditsForSelectors(questionId);
	}
	private static void addCreditsForSelectors(String questionId){
		String most_selected = mostSelection(questionId);
		ArrayList<AnswerForm> answerlist = getAnswersForQuestion(questionId);
		for(AnswerForm af : answerlist){
			String user_selection  = af.getSelection();
			if(user_selection.compareToIgnoreCase(most_selected)==0){
				String phone = af.getPhone();
				int credit = getCredit(questionId);
				addCreditForUser(phone, credit);
			}
		}
	}
	static final String TABLE_NAME = "questionnaireform";
	private static void setQuestionOff(String questionId){
		String sql = "update questionnaireform set state=? where id=?";
		Connection con = DBTools.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,"off");
			ps.setString(2,questionId);
			ps.execute();
			ps.close();
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static String mostSelection(String questionId){
		String result = AnswerModel.getMostSelection(questionId);
		return result;
	}
	private static void addCreditForUser(String phone,int credit){
//		int old_credit = Integer.parseInt(UserModel.getUser(phone).getCredit());
		User u = new User();
		u.setPhone(phone);
		int old_credit = UserModel.getCredit(u);
		int new_credit = old_credit + credit;
		UserModel.setCredit(new_credit, u);
	
	}
	/*
	 * 在answer表中查找所有符合的答案
	 */
	private static ArrayList<AnswerForm> getAnswersForQuestion(String questionId){
		
		return AnswerModel.getAnswersForQuestion(questionId);
	}
	
	private static int getCredit(String questionId){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		QuestionnaireForm qf = (QuestionnaireForm) session.get(QuestionnaireForm.class, Integer.parseInt(questionId));
		tx.commit();
		session.close();
		return Integer.parseInt(qf.getCredit());
	}
	

}
