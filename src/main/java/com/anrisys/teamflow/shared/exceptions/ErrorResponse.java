package com.anrisys.teamflow.shared.exceptions;

import java.time.Instant;
import java.util.List;

import com.anrisys.teamflow.shared.exceptions.common.BaseException;

public record ErrorResponse(String code, String message, Instant timestamp, List<FieldError> fields) {
	public static ErrorResponse fromException(BaseException ex) {
		return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), Instant.now(), null);
	}
	public static ErrorResponse withFields(String code, String message, List<FieldError> fields) {
		return new ErrorResponse(code, message, Instant.now(), fields);
	}

	public static ErrorResponse fromMessage(String code, String message) {
		return new ErrorResponse(code, message, Instant.now(), null);
	}
}
