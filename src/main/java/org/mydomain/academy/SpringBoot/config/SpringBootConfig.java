package org.mydomain.academy.SpringBoot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class SpringBootConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setAlwaysUseMessageFormat(true);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("classpath:messages");
		return messageSource;
	}

	@Value("${spring.datasource.driver-class-name}")
	private String databaseDriverClassName;
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	@Value("${spring.datasource.username}")
	private String databaseUsername;
	@Value("${spring.datasource.password}")
	private String databasePassword;

	@Bean
	public DataSource datasource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName(databaseDriverClassName);
		ds.setUrl(datasourceUrl);
		ds.setUsername(databaseUsername);
		ds.setPassword(databasePassword);
		return ds;
	}
}
