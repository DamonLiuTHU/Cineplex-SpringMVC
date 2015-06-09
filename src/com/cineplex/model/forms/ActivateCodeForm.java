package com.cineplex.model.forms;

public class ActivateCodeForm {


	private String activateCode;

	/**
	 * @return the code
	 */
	public String getCode() {
		return activateCode;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.activateCode = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivateCodeForm [activateCode=" + activateCode + "]";
	}	
	
}
