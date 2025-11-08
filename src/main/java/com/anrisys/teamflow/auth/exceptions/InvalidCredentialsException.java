package com.anrisys.teamflow.auth.exceptions;

import com.anrisys.teamflow.shared.exceptions.common.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
