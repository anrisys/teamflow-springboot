package com.anrisys.teamflow.auth.exceptions;

import com.anrisys.teamflow.shared.exceptions.common.BusinessLogicException;

public class EmailAlreadyExistsException extends BusinessLogicException {

	public EmailAlreadyExistsException() {
		super("email already exists");
	}

	public EmailAlreadyExistsException(Throwable cause) {
		super("email already exists", cause);
	}
}
