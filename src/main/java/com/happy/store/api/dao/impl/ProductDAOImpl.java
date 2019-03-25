package com.happy.store.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.happy.store.api.dao.AbstractDAO;
import com.happy.store.api.dao.ProductDAO;
import com.happy.store.api.model.Product;

@Repository("ProductDAO")
public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
	protected ProductDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Product searchBySku(String sku) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from "+PRODUCT_TABLE+" where sku = :sku limit 1");
		
		setSqlQuery(sqlBuilder.toString());
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("sku", sku);
        
        setParamsMap(paramsMap);
		return (Product)querySingleObject(Product.class);
	}

	@Override
	public List<Product> searchByPageLimit(int offset, int limit, List<Integer> categoryIdList, double startRange, double endRange) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from "+PRODUCT_TABLE);
		
		boolean categoryPresent = (categoryIdList!=null && !categoryIdList.isEmpty());
		boolean startRangePresent = (startRange > 0.0);
		boolean endRangePresent = (endRange > 0.0);
		
		if(categoryPresent || startRangePresent || endRangePresent) { 
			sqlBuilder.append(" where ");
		}
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(categoryPresent) {
			sqlBuilder.append(" category_id in (:categoryId)");
			paramsMap.put("categoryId", categoryIdList);
		}
		
		if(startRangePresent) {
			if(!paramsMap.isEmpty()) { sqlBuilder.append(" and "); }
			sqlBuilder.append(" selling_price >= :startPrice");
			paramsMap.put("startPrice", startRange);
		}
		
		if(endRangePresent) {
			if(!paramsMap.isEmpty()) { sqlBuilder.append(" and "); }
			sqlBuilder.append(" selling_price <= :endPrice");
			paramsMap.put("endPrice", endRange);
		}
		
		sqlBuilder.append(" limit :limit offset :offset");
		
		setSqlQuery(sqlBuilder.toString());
		
        paramsMap.put("offset", offset);
        paramsMap.put("limit", limit);
        
        setParamsMap(paramsMap);
		return queryObjectList(Product.class);
	}

	@Override
	public int totalCountByCondition(List<Integer> categoryIdList, double startRange, double endRange) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select count(*) total_count from "+PRODUCT_TABLE);
		
		boolean categoryPresent = (categoryIdList!=null && !categoryIdList.isEmpty());
		boolean startRangePresent = (startRange > 0.0);
		boolean endRangePresent = (endRange > 0.0);
		
		if(categoryPresent || startRangePresent || endRangePresent) { 
			sqlBuilder.append(" where ");
		}
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(categoryPresent) {
			sqlBuilder.append(" category_id in (:categoryId)");
			paramsMap.put("categoryId", categoryIdList);
		}
		
		if(startRangePresent) {
			if(!paramsMap.isEmpty()) { sqlBuilder.append(" and "); }
			sqlBuilder.append(" selling_price >= :startPrice");
			paramsMap.put("startPrice", startRange);
		}
		
		if(endRangePresent) {
			if(!paramsMap.isEmpty()) { sqlBuilder.append(" and "); }
			sqlBuilder.append(" selling_price <= :endPrice");
			paramsMap.put("endPrice", endRange);
		}
		
		setSqlQuery(sqlBuilder.toString());
		
        setParamsMap(paramsMap);
		return querySingleInteger();
	}
}
