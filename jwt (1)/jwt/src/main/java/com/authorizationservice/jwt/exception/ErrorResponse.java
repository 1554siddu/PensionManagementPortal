package com.authorizationservice.jwt.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	private LocalDateTime timestamp;
	
	@JsonInclude(Include.NON_NULL)
	private List<String> fieldErrors;
}