package com.happy.store.api.utils;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LocaleUtils {
	public static final String LOCALE_ENGLISH_US = "en_US";
	
	@Autowired
	public CustomStringUtils customStringUtils;
	
	/**
	 * Function that checks if valid country code is input
	 * 
	 * @param countryCode String 2-letter country code
	 * @return boolean true - input is valid, false - input is invalid
	 */
	public boolean isValidCountryCode(String countryCode) {
		boolean isValid = false;
		
		List<String> countryCodeList = Arrays.asList(Locale.getISOCountries());
		if(countryCodeList.contains(countryCode)) { isValid = true; }
		
		return isValid;
	}
	
	/**
	 * Function that checks if a language code is valid (case-sensitive)
	 * 
	 * @param languageCode String language code that includes the 2-letter language code with 2-letter country code
	 * @return boolean true - input is valid, false - input is invalid
	 */
	public boolean isValidLanguageCode(String languageCode) {
		boolean isValid = false;
		
		List<String> supportedLocaleList = Arrays.asList(LOCALE_ENGLISH_US);
		if(supportedLocaleList.contains(languageCode)) { isValid = true; }
		
		return isValid;
	}
	
	/**
	 * Function that checks if a valid currency code is input
	 * @param currencyCode String 3-letter currency code
	 * @return boolean true - input is valid, false - input is invalid
	 */
	public boolean isValidCurrencyCode(String currencyCode) {
		boolean isValid = false;
		
		try {
			Currency.getInstance(currencyCode);
			isValid = true;
		} catch(IllegalArgumentException e) {}
		
		return isValid;
	}
}
