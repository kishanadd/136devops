package com.srk.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> {
	private Integer httpStatus;
	private boolean status;
	private ResponseData<T> data;
	
	public SuccessResponse() {
		super();
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public ResponseData<T> getData() {
		return data;
	}

	public void setData(ResponseData<T> data) {
		this.data = data;
	}
}
