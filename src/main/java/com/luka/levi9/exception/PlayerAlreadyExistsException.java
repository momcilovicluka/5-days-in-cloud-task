package com.luka.levi9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlayerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5321395506214256957L;

	public PlayerAlreadyExistsException(String message) {
		super(message);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}