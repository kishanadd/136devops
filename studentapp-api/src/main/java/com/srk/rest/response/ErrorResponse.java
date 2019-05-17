package com.srk.rest.response;

/**
 * @author skummitha
 *
 */
public class ErrorResponse {

	/**
	 * 
	 */
	
	private int httpStatus;
	private String errorMessage;
	private String errorCode;
	private String developerMessage;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
