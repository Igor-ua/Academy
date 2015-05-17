package org.mydomain.academy.SpringBoot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/footer").setViewName("/fragments/footer");
		registry.addViewController("/body").setViewName("/fragments/body");
		registry.addViewController("/header").setViewName("/fragments/header");

		registry.addViewController("/news").setViewName("/fragments/news/news");

		//roles
		registry.addViewController("/student").setViewName("/fragments/roles/student");
		registry.addViewController("/teacher").setViewName("/fragments/roles/teacher");
		registry.addViewController("/admin").setViewName("/fragments/roles/admin");

		registry.addViewController("/db").setViewName("/fragments/db/db-body");
	}

}