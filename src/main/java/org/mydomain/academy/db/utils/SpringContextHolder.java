package org.mydomain.academy.db.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextHolder {

	private static ApplicationContext context;

	public SpringContextHolder(String config) {
		context = new ClassPathXmlApplicationContext(config);
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
