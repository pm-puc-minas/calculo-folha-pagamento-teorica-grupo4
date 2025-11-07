package com.trabalho.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValoresBordasException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValoresBordasException() {
		super();
	}

	public ValoresBordasException(String message) {
		super(message);
	}

	public ValoresBordasException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValoresBordasException(Throwable cause) {
		super(cause);
	}

}
