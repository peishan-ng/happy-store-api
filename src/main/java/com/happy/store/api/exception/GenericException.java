package com.happy.store.api.exception;

public class GenericException extends Exception {
	public static final String EXCEPTION_CODE_10001 = "10001"; // RestControllerAdvice
	
	protected String errorCode;
	
	public GenericException(String message) {
		super(message);
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
