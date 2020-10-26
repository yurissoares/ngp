package com.nativa.ngp.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nativa.ngp.exception.MarcaException;
import com.nativa.ngp.model.ErrorResponse;
import com.nativa.ngp.model.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(MarcaException.class)
	public ResponseEntity<ErrorResponse> handlerMarcaException(MarcaException m) {
		ErrorResponseBuilder erro = ErrorResponse.builder();
		
		erro.httpStatus(m.getHttpStatus().value());
		erro.mensagem(m.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
	}
}
