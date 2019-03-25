package com.happy.store.api.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class CustomStringUtils {
	public static final String SYMBOL_COMMA = ",";
	public static final String SYMBOL_EMPTY_SPACE = " ";
	public static final String SYMBOL_PIPE = "|";
	public static final String SYMBOL_COLON = ":";
	public static final String SYMBOL_SEMI_COLON = ";";
	public static final String SYMBOL_NEWLINE = "\n";
	public static final String DATE_FORMAT_SQL = "yyyy-MM-dd HH:mm:ss";
	public static final String CUSTOM_FOLDER = "custom_upload";
	public static final String HTML_BREAK = "<br />";
	
	public static final String SYSTEM_USER = "system";
	
	public static final int RANDOM_LENGTH = 16;
	public static final boolean RANDOM_USE_LETTER = true;
	public static final boolean RANDOM_USE_NUMBER = false;
	
	@Autowired
	public Environment env;
	@Autowired
	public MessageSource messageSource;
	
	/**
	 * Function that return a Timestamp object by first doing a backward calculation 
	 *  to minus number of minutes from the current datetime
	 * 
	 * @param queryInterval a numeric value (in minutes) to minus from the current datetime
	 * @return Timestamp object after subtracting number of minutes specified
	 */
	public Timestamp getCurrentDateWithOffset(String queryInterval) {
		int numQueryInterval = 10; 		
		if(StringUtils.isNotBlank(queryInterval)) { numQueryInterval = Integer.parseInt(queryInterval); }
		
		Calendar date = GregorianCalendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		date.add(Calendar.MINUTE, -numQueryInterval);
		
		return new Timestamp(date.getTimeInMillis());
	}
	
	/**
	 * Function that will convert a String into Integer value
	 * 
	 * @param intValue Value that wants to be converted
	 * @param defaultIntValue the default integer value to use, should the conversion fails
	 * @return integer value of the converted String or default value
	 */
	public int convertStringToInt(String intValue, int defaultIntValue) {
		int finalValue = defaultIntValue;
		try {
			finalValue = Integer.valueOf(intValue);
		} catch (NumberFormatException e) {}
		
		return finalValue;
	}
	
	/**
	 * Function that retrieve String value based on a given key in a Map<String, Object> object
	 * 
	 * @param resultMap Map object to have value retrieved
	 * @param key String key to specifically get its corresponding value
	 * @return String value from the corresponding key specified
	 */
	public String getMapDataAsString(Map<String, Object> resultMap, String key) {
		String data = null;
		if(isNotNullOrEmpty(resultMap) && isNotNullOrEmpty(key)) { data = (String)resultMap.get(key); }
		
		return data;
	}
	
	/**
	 * Function that retrieve Integer value based on a given key in a Map<String, Object> object
	 * 
	 * @param resultMap Map object to have value retrieved
	 * @param key String key to specifically get its corresponding value
	 * @return integer value from the corresponding key specified
	 */
	public int getMapDataAsInt(Map<String, Object> resultMap, String key) {
		int data = -1;
		if(isNotNullOrEmpty(resultMap) && isNotNullOrEmpty(key)) { 
			Object valueObj = resultMap.get(key);
			if(valueObj!=null) {
				data = Integer.valueOf(valueObj.toString()).intValue();
			}
		}
		
		return data;
	}
	
	/**
	 * Function that retrieve double value based on a given key in a Map<String, Object> object
	 * 
	 * @param resultMap Map object to have value retrieved
	 * @param key String key to specifically get its corresponding value
	 * @return double value from the corresponding key specified
	 */
	public double getMapDataAsDouble(Map<String, Object> resultMap, String key) {
		double data = -0.0;
		if(isNotNullOrEmpty(resultMap) && isNotNullOrEmpty(key)) { 
			Object valueObj = resultMap.get(key);
			if(valueObj!=null) {
				BigDecimal doubleVal = new BigDecimal(valueObj.toString());
				data = doubleVal.doubleValue();
			}
		}
		
		return data;
	}
	
	/**
	 * Function that checks if two String values are equal
	 * 
	 * @param item1 first String object to compare against
	 * @param item2 second String object to compare against
	 * @return boolean true - both String objects are equal in value, false - both String values are different
	 */
	public boolean isStringEquals(String item1, String item2) {
		boolean isStrEqual = false;
		
		if(StringUtils.isNotBlank(item1) && StringUtils.isNotBlank(item2)) {
			isStrEqual = item1.equalsIgnoreCase(item2);
		}
		
		return isStrEqual;
	}
	
	/**
	 * Function that check if an object is empty (empty String value) or null
	 * 
	 * @param item Object to check if it is null or empty (in String or list context)
	 * @return boolean true - given object is not null or empty, false given object is either empty in size, null or empty String and etc... 
	 */
	public boolean isNotNullOrEmpty(Object item) {
		boolean isItemNotEmpty = false;
		if(item!=null) {
			if(item instanceof JSONObject) {
				JSONObject jsonItem = (JSONObject) item;
				if(jsonItem!=null && jsonItem.length()>0) { isItemNotEmpty = true; }
			} else if(item instanceof List) {
				if(!((List<?>) item).isEmpty()) { isItemNotEmpty = true; }
			} else if(item instanceof Map) {
				if(!(((Map<?, ?>) item).isEmpty())) { isItemNotEmpty = true; }
			} else if(item instanceof String) {
				if(StringUtils.isNotEmpty((String)item)) { isItemNotEmpty = true; }
			} else {
				isItemNotEmpty = true;
			}
		}
		
		return isItemNotEmpty;
	}
	
	/**
	 * Function that create a new Timestamp object with current datetime
	 * @return Timestamp object with current datetime
	 */
	public Timestamp getCurrentTimestamp() {
		return new Timestamp(GregorianCalendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai")).getTimeInMillis());
	}
	
	public String properTextEncoding(String originalText) {
		try {
			return new String(originalText.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return originalText;
		}
	}
	
	public Timestamp stringToTimestamp(String dateObj, String dateFormat) {
		Timestamp convertedDate = null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		
		try {
			convertedDate = new Timestamp(format.parse(dateObj).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return convertedDate;
	}
	
	/**
	 * Function that convert from integer value to boolean
	 * @param value 1 - true, 0 - false
	 * @return Boolean object
	 */
	public boolean convertIntToBoolean(int value) {
		return value==1 ? true : false;
	}
	
	/**
	 * Function that convert from integer value to String word of "true" or "false"
	 * @param value 1 - true, 0 - false
	 * @return String value of either "true" or "false" only
	 */
	public String convertIntToBooleanString(int value) {
		return value==1 ? "true" : "false";
	}
	
	public JSONObject fromStringToJson(String jsonInString, List<String> innerJsonList) throws NullPointerException {
		JSONObject jsonObj = new JSONObject(jsonInString);
		if(!isNotNullOrEmpty(innerJsonList)) { return jsonObj; }
		
		for(String jsonKey : innerJsonList) { jsonObj = jsonObj.getJSONObject(jsonKey); }
		return jsonObj;
	}
	
	public String stringReplaceSeparator(String msg, String fromSeparator, String toSeparator) {
		if(isNotNullOrEmpty(msg)) {
			return StringUtils.join(msg.split(fromSeparator), toSeparator);
		}
		
		return msg;
	}
	
	/**
	 * Function that converts a String object into Long object
	 * 
	 * @param longValue String value to be converted as Long object
	 * @param defaultLongValue default value should the conversion fails
	 * @return Long object with either the converted or default value
	 */
	public Long convertStringToLong(String longValue, Long defaultLongValue) {
		Long finalValue = defaultLongValue;
		try {
			finalValue = Long.valueOf(longValue);
		} catch (NumberFormatException e) {}
		
		return finalValue;
	}
	
	/**
	 * Function that will retrieve the corresponding value from key found in config.properties file
	 * 
	 * @param envPropertyName String name of the property key
	 * @return String value of the property value
	 */
	public String getEnvProperty(String envPropertyName) {
		String propertyValue = "";
		if (env!=null && StringUtils.isNotBlank(envPropertyName)) {
			propertyValue = env.getProperty(getEnvironmentPrefix()+envPropertyName);
		}
		
		return propertyValue;
	}
	
	/**
	 * Function to check if execution is in sandbox or production
	 * 
	 * @return String either "sandbox" or ""
	 */
	public String getEnvironmentPrefix() {
		String envTestValue = env.getProperty("env.test");
		return (StringUtils.isNotBlank(envTestValue) && envTestValue.equalsIgnoreCase("0")) ? "" : "sandbox.";
	}
	
	/**
	 * Convert all null string object to empty string
	 * 
	 * @param stringObj String object to parse against
	 * @return String object in its value or empty string (if null)
	 */
	public String fromNullToEmptyString(String stringObj) {
		return (!isNotNullOrEmpty(stringObj) || stringObj.equalsIgnoreCase("null")) ? "" : stringObj;
	}
	
	/**
	 * Function that generates random ID, more for debugging purpose
	 * @return String random alphanumeric
	 */
	public String randomGeneratedId() {
		return RandomStringUtils.random(RANDOM_LENGTH, RANDOM_USE_LETTER, RANDOM_USE_NUMBER);
	}
	
	/**
	 * Function that will do a String concatenation by first checking if original String data is empty
	 * @param origStringData String original String data
	 * @param newStringData new String data to append
	 * @param separator String separator to use 
	 * @return String concatenated data
	 */
	public String stringConcatWithSeparator(String origStringData, String newStringData, String separator) {
		if(!isNotNullOrEmpty(origStringData)) { 
			origStringData = newStringData;
		} else {
			origStringData = String.format("%s%s%s", origStringData, separator, newStringData);
		}
		
		return origStringData;
	}
	
	/**
	 * Function that will convert a String into Double value
	 * 
	 * @param doubleValue Value that wants to be converted
	 * @param defaultDoubleValue default double value to use, should the conversion fails
	 * @return double value of the converted String or default value
	 */
	public double convertStringToDouble(String doubleValue, double defaultDoubleValue) {
		double finalValue = defaultDoubleValue;
		try {
			finalValue = Double.valueOf(doubleValue);
		} catch (NumberFormatException e) {}
		
		return finalValue;
	}
	
	/**
	 * To get i18n messages, retrieve from /resources/i18n/resource_message*
	 * Fixed it as Chinese Locale for now
	 * 
	 * @param messageKey String message key to retrieve
	 * @param addArgsArray argument specified in the message (if any)
	 * @return String value of the corresponding message value
	 */
	public String getI18nMessage(String messageKey, Object[] addArgsArray) {
		return messageSource.getMessage(messageKey, addArgsArray, messageKey, Locale.US);
	}
}
