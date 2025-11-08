package com.anrisys.teamflow.auth.exceptions;

import com.anrisys.teamflow.shared.exceptions.common.AuthenticationException;

public class JwtException extends AuthenticationException {
	public JwtException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public JwtException(String message) {
		super(message);
	}
}
