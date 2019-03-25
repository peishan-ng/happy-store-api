package com.happy.store.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.happy.store.api.exception.ApiProductException;
import com.happy.store.api.utils.CustomStringUtils;

@RestController
public abstract class AbstractController implements IController {	
	private final static Logger logger = LogManager.getLogger("AbstractController");
	
	@Autowired
	public CustomStringUtils customStringUtils;
	
	/**
	 * Function that creates a common response back to the caller
	 * 
	 * @param responseObject Object to display response data
	 * @param totalCount get the total number of result returned
	 * @param responseType response Type, JSON_SUCCESS or JSON_ERROR for now
	 * @return ResponseEntity object to return back to caller
	 */
	protected ResponseEntity<String> createResponse(Object responseObject, int totalCount, String responseType, String errorCode) {
		JSONObject responseJson = new JSONObject();
		String generatedId = customStringUtils.randomGeneratedId();
		
		try {
			switch(responseType) {
				case JSON_SUCCESS:
					responseJson.put(JSON_DATA, responseObject);
					break;
					
				case JSON_ERROR:
					responseJson.put(JSON_ERROR_CODE, errorCode);
					responseJson.put(JSON_ERROR_MESSAGE, responseObject);
					break;
					
				default:
					throw new ApiProductException("Response type is not supported!");
			}
			
		} catch(Exception e) {
			responseJson.put(JSON_ERROR_CODE, errorCode);
			responseJson.put(JSON_ERROR_MESSAGE, getErrorMessage(e));
			e.printStackTrace();
			logger.error(e);
			
		} finally {
			responseJson.put(JSON_RESPONSE, responseType);
			responseJson.put(JSON_RESPONSE_ID, generatedId);
			responseJson.put(JSON_TOTAL_COUNT, totalCount);
			
			logger.info(responseJson.toString());
		}
		
		return new ResponseEntity<String>(responseJson.toString(), createJsonHeader(), HttpStatus.OK);
	}
	
	/**
	 * Function that creates a JSON type header
	 * @return HttpHeaders
	 */
	private HttpHeaders createJsonHeader() {
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString());
	    
		return responseHeaders;
	}
	
	/**
	 * Function that retrieves exception's message
	 * @param e Exception 
	 * @return String message of the exception
	 */
	private String getErrorMessage(Exception e) {
		return e.getMessage();
	}
}
