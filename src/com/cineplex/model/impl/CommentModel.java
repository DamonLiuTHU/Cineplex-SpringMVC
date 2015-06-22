package com.cineplex.model.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
}
