package com.srk.exception;

public class CustomWebException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String errorMessage;

	public CustomWebException(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}