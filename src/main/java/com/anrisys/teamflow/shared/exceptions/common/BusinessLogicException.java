package com.anrisys.teamflow.shared.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class BusinessLogicException extends BaseException {
	public BusinessLogicException(String message) {
		super("ERROR_UNPROCESSABLE_ENTITY", message, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public BusinessLogicException(String message,Throwable cause) {
		super("ERROR_UNPROCESSABLE_ENTITY", message, HttpStatus.UNPROCESSABLE_ENTITY, cause);
	}
}
