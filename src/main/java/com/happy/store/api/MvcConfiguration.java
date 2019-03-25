package com.happy.store.api;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:config.properties") })
@ComponentScan(basePackages="com.happy.store")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {		
	public final String RESOURCE_BUNDLE_BASE = "i18n/resource_message";
	
	@Autowired
	private Environment env;
	
	/**
	 * Populate a data-source object in order to connect to the database for data manipulation 
	 * 
	 * @return data-source for the designated DB. Mysql in this case.
	 */
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.password"));
		
		dataSource.setMinIdle(stringToIntVal(env.getProperty("db.min.idle.conn")));
		dataSource.setMaxIdle(stringToIntVal(env.getProperty("db.max.idle.conn")));
		dataSource.setMaxOpenPreparedStatements(stringToIntVal(env.getProperty("db.max.open.prepared.stmt")));
		dataSource.setInitialSize(stringToIntVal(env.getProperty("db.initial.size")));
		dataSource.setMaxTotal(stringToIntVal(env.getProperty("db.max.concurrent.size")));
		dataSource.setMaxWaitMillis(stringToIntVal(env.getProperty("db.max.wait.ms")));
		
		return dataSource;
	}
	
	private int stringToIntVal(String strVal) {
		int intVal = -2;
		
		try {
			intVal = Integer.valueOf(strVal).intValue();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		return intVal;
	}
	
	@Bean(name="messageSource")
    public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:"+RESOURCE_BUNDLE_BASE);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(43200); // refresh cache after every 12 hours
        
        return messageSource;
    }
}
