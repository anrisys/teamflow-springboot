package com.anrisys.teamflow.auth.exceptions;

import com.anrisys.teamflow.shared.exceptions.common.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {

	public TokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TokenExpiredException(String message) {
		super(message);
	}

}
