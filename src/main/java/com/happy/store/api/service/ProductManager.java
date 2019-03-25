package com.happy.store.api.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.happy.store.api.exception.ApiProductException;

public interface ProductManager {
	public static final String PRODUCT_BRAND = "brand";
	public static final String PRODUCT_CATEGORY = "category";
	public static final String PRODUCT_SUBCATEGORY = "subCategory";
	public static final String PRODUCT_TITLE = "title";
	public static final String PRODUCT_DESCRIPTION = "description";
	public static final String PRODUCT_MEASUREMENT = "measurement";
	public static final String PRODUCT_COUNTRY_OF_ORIGIN_CODE = "countryOfOriginCode";
	public static final String PRODUCT_MATERIAL = "material";
	public static final String PRODUCT_WEIGHT = "weight";
	public static final String PRODUCT_GENDER = "gender";
	public static final String PRODUCT_IMAGES = "images";
	public static final String PRODUCT_COGS_CURRENCY_CODE = "cogsCurrencyCode";
	public static final String PRODUCT_COGS = "cogs";
	public static final String PRODUCT_LANGUAGE_CODE = "languageCode";
	public static final String PRODUCT_IS_ACTIVE = "isActive";
	
	public static final String PRODUCT = "product";
	public static final String PRODUCT_ID = "productId";
	public static final int RECORD_LIMIT = 8;
	
	public JSONObject populateProductBySku(String sku) throws ApiProductException;
	public JSONArray populateProductByPage(int page, List<Integer> categoryIdList, double startRange, double endRange) throws ApiProductException;
	public int getTotal();
}
