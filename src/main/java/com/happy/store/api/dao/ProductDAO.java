package com.happy.store.api.dao;

import java.util.List;

import com.happy.store.api.model.Product;

public interface ProductDAO {
	public static final String PRODUCT_TABLE = "product";
	
	public Product searchBySku(String sku);
	public List<Product> searchByPageLimit(int offset, int limit, List<Integer> categoryIdList, double startRange, double endRange);
	public int totalCountByCondition(List<Integer> categoryIdList, double startRange, double endRange);
}
