package com.cineplex.model.tables;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Questionnaire {
	@Id private int id; 
	private String waiterId;
	private String movieId;
	private String publishdate;
	private String duedate;
	private int credit;
	private String description;
	private String selection1;
	private String selection2;
	private String selection3;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	public Questionnaire(String waiterId, String movieId, String publishdate,
			String duedate, int credit, String description, String selection1,
			String selection2, String selection3) {
		super();
		this.waiterId = waiterId;
		this.movieId = movieId;
		this.publishdate = publishdate;
		this.duedate = duedate;
		this.credit = credit;
		this.description = description;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
	}
	public Questionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
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
	public Questionnaire(int id, String waiterId, String movieId,
			String publishdate, String duedate, int credit, String description,
			String selection1, String selection2, String selection3) {
		super();
		this.id = id;
		this.waiterId = waiterId;
		this.movieId = movieId;
		this.publishdate = publishdate;
		this.duedate = duedate;
		this.credit = credit;
		this.description = description;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Questionnaire [id=" + id + ", waiterId=" + waiterId
				+ ", movieId=" + movieId + ", publishdate=" + publishdate
				+ ", duedate=" + duedate + ", credit=" + credit
				+ ", description=" + description + ", selection1=" + selection1
				+ ", selection2=" + selection2 + ", selection3=" + selection3
				+ "]";
	}
	
}
