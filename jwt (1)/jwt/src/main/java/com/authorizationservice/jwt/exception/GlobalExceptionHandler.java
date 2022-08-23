package com.authorizationservice.jwt.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles input validation errors
	 * 
	 * @param MethodArgumentNotValidException ex
	 * @return ResponseEntity with error messages
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage("Invalid Credentials");
		response.setTimestamp(LocalDateTime.now());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

		response.setFieldErrors(errors);

		return new ResponseEntity<>(response, headers, status);
	}


	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handlesUserNotFoundException(
			InvalidCredentialsException invalidCredentialsException) {
		String errorMessage = invalidCredentialsException.getMessage();
		ErrorResponse response = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handlesTokenInvalidException(InvalidTokenException invalidTokenException) {
		String errorMessage = invalidTokenException.getMessage();
		ErrorResponse response = new ErrorResponse(errorMessage, LocalDateTime.now(), Collections.singletonList(errorMessage));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}