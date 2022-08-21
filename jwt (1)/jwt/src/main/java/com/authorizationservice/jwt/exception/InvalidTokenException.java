package com.authorizationservice.jwt.exception;

/**
 * Class to handle Invalid Credentials given by the user
 *
 */
public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = -3700741193887093791L;

	public InvalidTokenException(String message) {
		super(message);
	}
}