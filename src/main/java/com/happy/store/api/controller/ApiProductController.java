package com.happy.store.api.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happy.store.api.exception.ApiProductException;
import com.happy.store.api.service.ProductManager;

@RestController
@RequestMapping("/product")
public class ApiProductController extends AbstractController {
	private final static Logger logger = LogManager.getLogger("ApiProductController");
	
	@Autowired
	public ProductManager productManager;
	
	@RequestMapping(value = "/{sku}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<String> getProductById(@PathVariable String sku) throws ApiProductException {
		logger.info(String.format("SKU: %s", sku));
		
		JSONObject resultJson = productManager.populateProductBySku(sku);
		int totalCount = productManager.getTotal();
		return createResponse(resultJson, totalCount, JSON_SUCCESS, null);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<String> getProductList(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "categories", required = false) List<Integer> categoryList,
			@RequestParam(value = "start_range", required = false) String startRange,
			@RequestParam(value = "end_range", required = false) String endRange) throws ApiProductException {
		logger.info(String.format("Page: %s, category: %s, start_range: %s, end_range: %s", page, categoryList, startRange, endRange));
		
		int pageNo = 1;
		if(StringUtils.isNoneBlank(page)) { pageNo = Integer.valueOf(page); }
		
		double startRangeNo = 0.0;
		if(StringUtils.isNoneBlank(startRange)) { startRangeNo = Double.valueOf(startRange); }
		
		double endRangeNo = 0.0;
		if(StringUtils.isNoneBlank(endRange)) { endRangeNo = Double.valueOf(endRange); }
		
		JSONArray resultJson = productManager.populateProductByPage(pageNo, categoryList, startRangeNo, endRangeNo);
		int totalCount = productManager.getTotal();
		return createResponse(resultJson, totalCount, JSON_SUCCESS, null);
	}
}
