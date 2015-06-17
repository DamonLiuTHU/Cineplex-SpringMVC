package com.cineplex.model.forms;

public class UserRankForm {
	private String score;
	private String comment;
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
		return "UserRankForm [score=" + score + ", comment=" + comment + "]";
	}
	
}
