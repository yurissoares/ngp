package com.nativa.ngp.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PatrimonioException extends RuntimeException {

	private static final long serialVersionUID = 1458985307290388939L;

	private final HttpStatus httpStatus;

	public PatrimonioException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}

}
