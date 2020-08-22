package com.saif.nisum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.saif.nisum.exception.UserManagementServiceErrorCodes;
import com.saif.nisum.model.ServiceExceptionWrapper;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ServiceExceptionWrapper> handleUnknownException(Exception ex, WebRequest request) {
		logger.error("User service -> exception occurred " + ex.getMessage(), ex);
		ex.printStackTrace();

		String code = UserManagementServiceErrorCodes.GENERAL_EXCEPTION.getMessage();

		String message = "Error Occurred";
		ServiceExceptionWrapper exception = new ServiceExceptionWrapper(message, code);

		return new ResponseEntity<ServiceExceptionWrapper>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
