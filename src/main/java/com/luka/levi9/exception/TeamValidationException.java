package com.luka.levi9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@StandardException
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamValidationException extends RuntimeException {

	private static final long serialVersionUID = -7317190995382988861L;
}