package com.happy.store.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.happy.store.api.exception.ApiProductException;
import com.happy.store.api.exception.GenericException;
import com.happy.store.api.utils.CustomStringUtils;

@RequestMapping(produces = "application/json")
@RestControllerAdvice
public class ApiRestControllerAdvice extends ResponseEntityExceptionHandler implements IController {
	private final static Logger logger = LogManager.getLogger("RestControllerAdvice");
	
	@Autowired
	public CustomStringUtils customStringUtils;
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, 
			WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, 
			WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(status.getReasonPhrase(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, 
			WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@ExceptionHandler(ApiProductException.class)
	public ResponseEntity<Object> handleApiProductException(ApiProductException ex, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) {
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleException(GenericException ex, WebRequest request) {
		logger.error("Base Exception captured: ");
		ex.printStackTrace();
		return jsonErrorEntityResponse(ex.getMessage(), ex, request);
	}
	
	private ResponseEntity<Object> jsonErrorEntityResponse(String customMessage, Exception e, WebRequest request) {
		JSONObject errorJson = new JSONObject();
		
		String generatedId = customStringUtils.randomGeneratedId();
		String errorMsg = String.format("%s: %s", request.getDescription(false), customMessage);
		String errorCode = GenericException.EXCEPTION_CODE_10001;
		
		if(e instanceof GenericException) { 
			errorCode = ((GenericException) e).getErrorCode(); // Get custom error code (if available)
		}
		
		errorJson.put(JSON_RESPONSE, JSON_ERROR);
		errorJson.put(JSON_DATA, errorMsg);
		errorJson.put(JSON_ERROR_CODE, errorCode);
		errorJson.put(JSON_RESPONSE_ID, generatedId);
		
		logger.error(errorJson);
		logger.error(e);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<Object>(errorJson.toString(), headers, HttpStatus.OK);
	}
}
