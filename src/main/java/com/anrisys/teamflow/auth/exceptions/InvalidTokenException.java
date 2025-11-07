package com.anrisys.teamflow.auth.exceptions;

import com.anrisys.teamflow.shared.exceptions.common.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

	public InvalidTokenException(String message) {
		super(message);
	}
	
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
