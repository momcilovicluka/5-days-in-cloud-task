package com.luka.levi9.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5321395506214256957L;

	public AlreadyExistsException(String message) {
		super(message);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}