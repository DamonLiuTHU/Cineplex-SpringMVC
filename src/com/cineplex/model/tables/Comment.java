package com.cineplex.model.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) private int Id;
	private String score;
	private String comment;
	private String movieId;
	private String email;
	
	
	
	public Comment(int id, String score, String comment, String movieId,
			String email) {
		super();
		Id = id;
		this.score = score;
		this.comment = comment;
		this.movieId = movieId;
		this.email = email;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Comment [Id=" + Id + ", score=" + score + ", comment="
				+ comment + ", movieId=" + movieId + ", email=" + email + "]";
	}
	public Comment(String score, String comment, String movieId, String email) {
		super();
		this.score = score;
		this.comment = comment;
		this.movieId = movieId;
		this.email = email;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
