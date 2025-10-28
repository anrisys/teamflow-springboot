package com.anrisys.teamflow.shared.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class ResourceNotFoundException extends BaseException {

	public ResourceNotFoundException(String entity) {
		super("NOT_FOUND", String.format("%s is not found", entity), HttpStatus.NOT_FOUND);
	}

	public ResourceNotFoundException(String entity, Throwable cause) {
		super("NOT_FOUND", String.format("%s is not found", entity), HttpStatus.NOT_FOUND, cause);
	}
}
