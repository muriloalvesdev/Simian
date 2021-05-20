package br.com.simian.controller.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.simian.domain.model.ApiException;
import br.com.simian.exception.DNAInvalidException;
import br.com.simian.exception.DNANotSimianException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DNAInvalidException.class, DNANotSimianException.class })
	public ResponseEntity<ApiException> handleDNAInvalidException(RuntimeException runtimeException) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(createResponse(runtimeException.getMessage(), HttpStatus.FORBIDDEN.value()));
	}

	private ApiException createResponse(String message, int value) {
		return new ApiException(message, value);
	}

}
