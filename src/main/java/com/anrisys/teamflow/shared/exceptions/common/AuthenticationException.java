package com.anrisys.teamflow.shared.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class AuthenticationException extends BaseException {

	public AuthenticationException(String message) {
		super("UNAUTHENTICATED", message, HttpStatus.UNAUTHORIZED);
	}

	public AuthenticationException(String message, Throwable cause) {
		super("UNAUTHENTICATED", message, HttpStatus.UNAUTHORIZED, cause);
	}
}
