package com.happy.store.api.controller;

public interface IController {
	/*
	 * Below is a sample response of success situation
	   	{
		  "data": {}, 
		  "total": 0,
		  "response": "success", 
		  "response_id": ""
		}
	 */
	
	/*
	 * Below is a sample response of error situation
	 	{
		  "error_message": "123456", 
		  "error_code": "100001",
		  "total": 0,
		  "response": "error", 
		  "response_id": ""
		}
	 */
	public static final String JSON_ERROR = "error";
	public static final String JSON_ERROR_CODE = "error_code";
	public static final String JSON_ERROR_MESSAGE = "error_message";
	
	public static final String JSON_SUCCESS = "success";
	public static final String JSON_RESPONSE = "response";
	public static final String JSON_TOTAL_COUNT = "total";
	
	public static final String JSON_RESPONSE_ID = "response_id";
	public static final String JSON_DATA = "data";
}
