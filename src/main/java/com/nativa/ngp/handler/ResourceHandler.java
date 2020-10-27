package com.nativa.ngp.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nativa.ngp.exception.MarcaException;
import com.nativa.ngp.exception.PatrimonioException;
import com.nativa.ngp.model.Response;

@ControllerAdvice
public class ResourceHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {
		
		Map<String, String> erros = new HashMap<>();
		m.getBindingResult().getAllErrors().forEach( erro -> {
			String campo = ((FieldError) erro).getField();
			String mensagem = erro.getDefaultMessage();
			erros.put(campo, mensagem);
		});
		
		Response<Map<String, String>> response = new Response<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(erros);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MarcaException.class)
	public ResponseEntity<Response<String>> handlerMarcaException(MarcaException m) {
		Response<String> response = new Response<>();
		response.setStatusCode(m.getHttpStatus().value());
		response.setData(m.getMessage());
		return ResponseEntity.status(m.getHttpStatus()).body(response);
	}
	
	@ExceptionHandler(PatrimonioException.class)
	public ResponseEntity<Response<String>> handlerPatrimonioException(PatrimonioException m) {
		Response<String> response = new Response<>();
		response.setStatusCode(m.getHttpStatus().value());
		response.setData(m.getMessage());
		return ResponseEntity.status(m.getHttpStatus()).body(response);
	}
}
