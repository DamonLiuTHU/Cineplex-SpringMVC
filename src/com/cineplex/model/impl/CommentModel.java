package com.cineplex.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.cineplex.model.tables.Comment;

public class CommentModel {
	
	public static void save(Comment comment){
		if(comment.getMovieId() == null){
			return;
		}
		
		Session session = ModelManager.sharedInstance().getSession();
		Transaction tx = session.beginTransaction();
		session.save(comment);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Comment> getComments(String movieId){
		List<Comment> result = new ArrayList<Comment>();
		Session  s = ModelManager.sharedInstance().getSession();
		Criterion c = Restrictions.eq("movieId", movieId);
		Criteria criteria = s.createCriteria(Comment.class);
		criteria.add(c);
		result = criteria.list();
		return result;
	}
	
}
