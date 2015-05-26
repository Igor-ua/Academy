package org.mydomain.academy.SpringBoot.config;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

public class PropertyManager {

	private static final String PROPERTIES_PATH = "src/main/resources/application.properties";
	private String url;
	private int port;
	private Properties properties;

	public PropertyManager() {
		loadPropertyFile(PROPERTIES_PATH);
		parsePort();
	}

	public PropertyManager(String path) {
		checkNotNull(path);
		loadPropertyFile(path);
		parsePort();
	}

	private void loadPropertyFile(String path) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(path)));
		} catch (IOException e) {
			System.err.println("Error loading properties from path: " + path);
		}
	}

	private void parsePort() {
		url = properties.getProperty("spring.datasource.url");
		String parsedUrl = StringUtils.substringBetween(url, "//", "/");
		port = Integer.parseInt(StringUtils.substringAfter(parsedUrl, ":"));
	}

	public int getPort() {
		return port;
	}
}
