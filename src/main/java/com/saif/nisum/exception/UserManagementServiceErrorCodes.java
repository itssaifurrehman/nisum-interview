package com.saif.nisum.exception;

import org.springframework.http.HttpStatus;

public enum UserManagementServiceErrorCodes implements ServiceErrorMsg {

	USER_ALREADY_EXISTS("Email already exists", HttpStatus.BAD_REQUEST),

	USERID_NOT_FOUND("User ID not found", HttpStatus.NOT_FOUND),

	CRITERIA_NOT_FOUND("Criteria not found", HttpStatus.BAD_REQUEST),

	EMPTY_DATABASE("No Data Found", HttpStatus.NOT_FOUND),

	GENERAL_EXCEPTION("General Exception", HttpStatus.BAD_REQUEST);

	private String message;
	private HttpStatus httpStatus;
	private String errorDetail;

	private UserManagementServiceErrorCodes(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	private UserManagementServiceErrorCodes(String message) {
		this.message = message;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
