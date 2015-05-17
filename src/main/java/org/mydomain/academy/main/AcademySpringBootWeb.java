package org.mydomain.academy.main;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = "org.mydomain.academy")
@EnableJpaRepositories(basePackages = "org.mydomain.academy/db/DAO/JPAInterface")
@EntityScan(basePackages = "org.mydomain.academy/db/entities")
public class AcademySpringBootWeb extends WebMvcConfigurerAdapter implements CommandLineRunner {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AcademySpringBootWeb.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.err.println("Spring Boot is ready...");
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

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setAlwaysUseMessageFormat(true);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("classpath:messages");

		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.getDefault());
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setPageParameterName("page.page");
		resolver.setSizeParameterName("page.size");
		resolver.setOneIndexedParameters(true);
		resolver.setFallbackPageable(new PageRequest(0, 10));
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}
}
