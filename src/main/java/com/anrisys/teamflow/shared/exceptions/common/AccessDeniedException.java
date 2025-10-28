package com.anrisys.teamflow.shared.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class AccessDeniedException extends BaseException {
    public AccessDeniedException(String message) {
        super("ACCESS_DENIED", message, HttpStatus.FORBIDDEN);
    }

	public AccessDeniedException(String message, Throwable cause) {
		super("ACCESS_DENIED", message, HttpStatus.FORBIDDEN, cause);
	}
}
