package com.cineplex.model.forms;

public class UserRankForm {
	private String score;
	private String comment;
	private String movieId;
	public UserRankForm(String score, String comment, String movieId) {
		super();
		this.score = score;
		this.comment = comment;
		this.movieId = movieId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
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
	public UserRankForm() {
		super();
	}
	public UserRankForm(String score, String comment) {
		super();
		this.score = score;
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "UserRankForm [score=" + score + ", comment=" + comment
				+ ", movieId=" + movieId + "]";
	}
	
}
