package com.happy.store.api.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.happy.store.api.utils.CustomStringUtils;

public class AbstractDAO implements IDAO {
	private final static Logger logger = LogManager.getLogger("AbstractDAO");
	
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private String sqlQuery;
	private Map<String, Object> paramsMap = null;
	
	@Autowired
	public CustomStringUtils customStringUtils;
	
	protected AbstractDAO(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	@Override
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	protected void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
	
	// query a list of objects by a given Class
	protected <T> List<T> queryObjectList(Class<T> mappingClass) throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.query(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), new BeanPropertyRowMapper<T>(mappingClass));
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e) {
	    	logger.error(e);
	    	throw e;
	    }
	}
	
	// query a list of integer values
	protected List<Integer> queryIntegerList() throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.queryForList(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	// query a list of String values
	protected List<String> queryStringList() throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.queryForList(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), String.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	// batch update by a given list of Objects
	protected int[] batchUpdateQuery(List<?> objectList) {
		return namedParameterJdbcTemplate.batchUpdate(this.sqlQuery, SqlParameterSourceUtils.createBatch(objectList.toArray()));
	}
	
	// update single entry in the database by a single object
	protected int updateSingleRecord(Object mappingClass) throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.update(this.sqlQuery, new BeanPropertySqlParameterSource(mappingClass));
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	// update single entry in the database by a single object
	protected int updateSingleRecord(BeanPropertySqlParameterSource namedParameters) throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.update(this.sqlQuery, namedParameters);
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		} 
	}
	
	// insert a single new entry to the database
	protected int insertSingleRecord(Object mappingClass, String[] primaryKeyColumnArray) throws DataAccessException, InvalidDataAccessApiUsageException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			namedParameterJdbcTemplate.update(this.sqlQuery, new BeanPropertySqlParameterSource(mappingClass), keyHolder, primaryKeyColumnArray);
			try {
				return keyHolder.getKey().intValue();
			} catch(InvalidDataAccessApiUsageException e1) {
				logger.error(e1);
				throw e1;
			}
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	// query single entry, returning the full object data
	protected <T> Object querySingleObject(Class<T> clazz) throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.queryForObject(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), new BeanPropertyRowMapper<T>(clazz));
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e1) {
			logger.error(e1);
			throw e1;
		} 
	}
	
	// query single entry, returning a String data
	protected String querySingleString() throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.queryForObject(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), String.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	// query single entry, returning a Integer data
	protected Integer querySingleInteger() throws DataAccessException {
		try {
			return namedParameterJdbcTemplate.queryForObject(this.sqlQuery, new MapSqlParameterSource(this.paramsMap), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return -1;
		} catch(DataAccessException e) {
			logger.error(e);
			throw e;
		}
	}
}
