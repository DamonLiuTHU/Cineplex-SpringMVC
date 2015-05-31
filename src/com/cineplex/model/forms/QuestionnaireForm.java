package com.cineplex.model.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class QuestionnaireForm {
	@Id
	@GeneratedValue
	int id;
	String description;
	String selection1;
	String selection2;
	String selection3;
	String duedate;
	String credit;
	String publishdate;
	String waiterId;
	String movieId;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the selection1
	 */
	public String getSelection1() {
		return selection1;
	}
	/**
	 * @param selection1 the selection1 to set
	 */
	public void setSelection1(String selection1) {
		this.selection1 = selection1;
	}
	/**
	 * @return the selection2
	 */
	public String getSelection2() {
		return selection2;
	}
	/**
	 * @param selection2 the selection2 to set
	 */
	public void setSelection2(String selection2) {
		this.selection2 = selection2;
	}
	/**
	 * @return the selection3
	 */
	public String getSelection3() {
		return selection3;
	}
	/**
	 * @param selection3 the selection3 to set
	 */
	public void setSelection3(String selection3) {
		this.selection3 = selection3;
	}
	/**
	 * @return the duedate
	 */
	public String getDuedate() {
		return duedate;
	}
	/**
	 * @param duedate the duedate to set
	 */
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	/**
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public QuestionnaireForm(int id, String description, String selection1,
			String selection2, String selection3, String duedate, String credit) {
		super();
		this.id = id;
		this.description = description;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
		this.duedate = duedate;
		this.credit = credit;
	}
	public QuestionnaireForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionnaireForm [id=" + id + ", description=" + description
				+ ", selection1=" + selection1 + ", selection2=" + selection2
				+ ", selection3=" + selection3 + ", duedate=" + duedate
				+ ", credit=" + credit + "]";
	}
	/**
	 * @return the publishdate
	 */
	public String getPublishdate() {
		return publishdate;
	}
	/**
	 * @param publishdate the publishdate to set
	 */
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	/**
	 * @return the waiterId
	 */
	public String getWaiterId() {
		return waiterId;
	}
	/**
	 * @param waiterId the waiterId to set
	 */
	public void setWaiterId(String waiterId) {
		this.waiterId = waiterId;
	}
	/**
	 * @return the movieId
	 */
	public String getMovieId() {
		return movieId;
	}
	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public QuestionnaireForm(int id, String description, String selection1,
			String selection2, String selection3, String duedate,
			String credit, String publishdate, String waiterId, String movieId) {
		super();
		this.id = id;
		this.description = description;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
		this.duedate = duedate;
		this.credit = credit;
		this.publishdate = publishdate;
		this.waiterId = waiterId;
		this.movieId = movieId;
	}
	
	
	
	
}
