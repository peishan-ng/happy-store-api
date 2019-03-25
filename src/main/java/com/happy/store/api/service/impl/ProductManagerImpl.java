package com.happy.store.api.service.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happy.store.api.dao.ProductDAO;
import com.happy.store.api.exception.ApiProductException;
import com.happy.store.api.model.Product;
import com.happy.store.api.service.AbstractManager;
import com.happy.store.api.service.ProductManager;

@Service("ProductManager")
public class ProductManagerImpl extends AbstractManager implements ProductManager {
	private int totalCount;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public JSONObject populateProductBySku(String sku) throws ApiProductException {
		JSONObject resultJson = new JSONObject();
		this.totalCount = 0;
		
		String errorSkuNotFound = customStringUtils.getI18nMessage("sku.not.found", new String[] {sku});
		if(!customStringUtils.isNotNullOrEmpty(sku)) { 
			throw new ApiProductException(errorSkuNotFound); 
		}
		
		Product prdtObj = productDAO.searchBySku(sku);
		try {
			if(customStringUtils.isNotNullOrEmpty(prdtObj)) {
				resultJson = new JSONObject(new ObjectMapper().writeValueAsString(prdtObj));
				this.totalCount = 1;
			} else {
				throw new ApiProductException(errorSkuNotFound);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return resultJson;
	}

	@Override
	public JSONArray populateProductByPage(int page, List<Integer> categoryIdList, double startRange, double endRange) throws ApiProductException {
		JSONArray resultArray = new JSONArray();
		this.totalCount = 0;
		
		int offset = 1;
		if(page < 1) { page = 1; }
		if(page > 0) { offset = (page-1)*RECORD_LIMIT; }
		if(startRange < 0.0) { startRange = 0.0; }
		if(endRange < 0.0) { endRange = 0.0; }
		
		List<Product> prdtList = productDAO.searchByPageLimit(offset, RECORD_LIMIT, categoryIdList, startRange, endRange);
		
		try {
			resultArray = new JSONArray(new ObjectMapper().writeValueAsString(prdtList));
			this.totalCount = productDAO.totalCountByCondition(categoryIdList, startRange, endRange);
		} catch (JSONException | JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return resultArray;
	}

	@Override
	public int getTotal() {
		return this.totalCount;
	}
}
