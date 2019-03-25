package com.happy.store.api.exception;

public class ApiProductException extends GenericException {
	public static final String EXCEPTION_CODE_20001 = "20001"; 
	public static final String EXCEPTION_CODE_20002 = "20002";
	
	public ApiProductException(String message) {
		super(message);
		this.errorCode = EXCEPTION_CODE_20001;
	}
}
