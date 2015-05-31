package com.cineplex.model.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnswerForm {
	
	@Id
	@GeneratedValue
	String id;
	String phone;
	String qId;
	String selection;
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the qId
	 */
	public String getqId() {
		return qId;
	}
	/**
	 * @param qId the qId to set
	 */
	public void setqId(String qId) {
		this.qId = qId;
	}
	/**
	 * @return the selection
	 */
	public String getSelection() {
		return selection;
	}
	/**
	 * @param selection the selection to set
	 */
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public AnswerForm(String phone, String qId, String selection) {
		super();
		this.phone = phone;
		this.qId = qId;
		this.selection = selection;
	}
	public AnswerForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerForm(String qId, String selection) {
		super();
		this.qId = qId;
		this.selection = selection;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnswerForm [phone=" + phone + ", qId=" + qId + ", selection="
				+ selection + "]";
	}
	
}
