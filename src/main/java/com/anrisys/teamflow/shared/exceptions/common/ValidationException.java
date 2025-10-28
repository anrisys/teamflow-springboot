package com.anrisys.teamflow.shared.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class ValidationException extends BaseException {

	public ValidationException(String message) {
		super("VALIDATION_EXCEPTION", message, HttpStatus.BAD_REQUEST);
	}

	public ValidationException(String message, Throwable cause) {
		super("VALIDATION_EXCEPTION", message, HttpStatus.BAD_REQUEST, cause);
	}

}
