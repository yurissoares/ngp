package com.nativa.ngp.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 2060370373962120712L;
	
	private final HttpStatus httpStatus;

	public UserException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}
