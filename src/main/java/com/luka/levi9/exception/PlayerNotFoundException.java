package com.luka.levi9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1616826993751458020L;

	public PlayerNotFoundException(String message) {
		super(message);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}