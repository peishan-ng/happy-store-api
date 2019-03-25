package com.happy.store.api.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.happy.store.api.utils.CustomStringUtils;
import com.happy.store.api.utils.LocaleUtils;

public abstract class AbstractManager implements IManager {
	public static final double DEFAULT_ZERO_DOUBLE_VALUE = 0.0;
	public static final int DEFAULT_NEGATIVE_INT_VALUE = -1;
	
	@Autowired
	public CustomStringUtils customStringUtils;
	@Autowired
	public LocaleUtils localeUtils;
	
	/**
	 * Function that checks for missing required data 
	 * 
	 * @param stringFieldList a list of fields name to check for missing required data
	 * @param dataJson JSON object that needs to check the data against
	 * @return concatenated String data of fields with missing data (if any)
	 */
	protected String checkForEmptyData(String missingFields, List<String> stringFieldList, JSONObject dataJson) {
		for(String field : stringFieldList) {
			if(!dataJson.has(field) || dataJson.isNull(field)) { 
				missingFields = customStringUtils.stringConcatWithSeparator(missingFields, field, CustomStringUtils.SYMBOL_COMMA);
				
			} else {
				Object data = dataJson.get(field);
				if(!customStringUtils.isNotNullOrEmpty(data)) { // check for empty data
					missingFields = customStringUtils.stringConcatWithSeparator(missingFields, field, CustomStringUtils.SYMBOL_COMMA);
				}
			}
		}
		
		return missingFields;
	}
	
	/**
	 * Function that checks for valid supported language code
	 * 
	 * @param existingStrField String existing list of incorrect fields with invalid data
	 * @param detailJson full Json object of the information sent from external source 
	 * @param fieldName String field name of the corresponding data
	 * @param isSkipPrdtCheck true - to skip mandatory checking, false - cannot skip the field checking
	 * @return String field name of the incorrect data (if any)
	 */
	protected String checkForDataValidity(String existingStrField, JSONObject detailJson, String fieldName, boolean isSkipPrdtCheck) {
		String fieldData = detailJson.isNull(fieldName) ? null : detailJson.getString(fieldName);
		if(isSkipPrdtCheck && !customStringUtils.isNotNullOrEmpty(fieldData)) { return existingStrField; }
		
		switch(fieldName) {
			case ProductManager.PRODUCT_LANGUAGE_CODE:
				if(!localeUtils.isValidLanguageCode(fieldData)) {
					existingStrField = customStringUtils.stringConcatWithSeparator(existingStrField, fieldName, CustomStringUtils.SYMBOL_COMMA);
				}
				
				break;
			case ProductManager.PRODUCT_GENDER:
				if(!customStringUtils.isNotNullOrEmpty(fieldData)) {
					existingStrField = customStringUtils.stringConcatWithSeparator(existingStrField, fieldName, CustomStringUtils.SYMBOL_COMMA);
				}
				
				break;
			case ProductManager.PRODUCT_COGS_CURRENCY_CODE:
				if(!localeUtils.isValidCurrencyCode(fieldData)) {
					existingStrField = customStringUtils.stringConcatWithSeparator(existingStrField, fieldName, CustomStringUtils.SYMBOL_COMMA);
				}
				break;
		}
		
		return existingStrField;
	}
	
	/**
	 * Function that checks for valid double value
	 * 
	 * @param existingStrField String existing list of incorrect fields with invalid data
	 * @param stringFieldList a list of fields name to check for missing required data
	 * @param dataJson JSON object that needs to check the data against
	 * @return String fieldname of the incorrect data (if any)
	 */
	protected String checkForValidDoubleValue(String existingStrField, List<String> stringFieldList, JSONObject dataJson) {
		for(String field : stringFieldList) {
			if(!dataJson.has(field) || dataJson.isNull(field)) { continue; }
			
			String data = (String)dataJson.get(field);
			if(customStringUtils.isNotNullOrEmpty(data)) {
				
				double convertedValue = customStringUtils.convertStringToDouble(data, DEFAULT_ZERO_DOUBLE_VALUE);
				if(convertedValue==DEFAULT_ZERO_DOUBLE_VALUE) {
					existingStrField = customStringUtils.stringConcatWithSeparator(existingStrField, field, CustomStringUtils.SYMBOL_COMMA);
				}
			}
		}
		
		return existingStrField;
	}
	
	/**
	 * Function that checks for valid integer value
	 * 
	 * @param existingStrField String existing list of incorrect fields with invalid data
	 * @param stringFieldList a list of fields name to check for missing required data
	 * @param dataJson JSON object that needs to check the data against
	 * @return String field name of the incorrect data (if any)
	 */
	protected String checkForValidIntValue(String existingStrField, List<String> stringFieldList, JSONObject dataJson) {
		for(String field : stringFieldList) {
			if(!dataJson.has(field) || dataJson.isNull(field)) { continue; }
			
			String data = (String)dataJson.get(field);
			if(customStringUtils.isNotNullOrEmpty(data)) {
				
				int convertedValue = customStringUtils.convertStringToInt(data, DEFAULT_NEGATIVE_INT_VALUE);
				if(convertedValue==DEFAULT_NEGATIVE_INT_VALUE) {
					existingStrField = customStringUtils.stringConcatWithSeparator(existingStrField, field, CustomStringUtils.SYMBOL_COMMA);
				}
			}
		}
		
		return existingStrField;
	}
}

