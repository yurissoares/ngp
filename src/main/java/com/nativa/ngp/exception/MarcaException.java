package com.nativa.ngp.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MarcaException extends RuntimeException {
	
	private static final long serialVersionUID = 5584796611141683251L;
	
	private final HttpStatus httpStatus;
	
	public MarcaException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}

}
