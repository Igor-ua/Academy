package org.mydomain.academy.db.utils;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.mydomain.academy.db.utils.LogMessages.*;

public class SingleConnectionManager implements ConnectionManager {

	private static final Logger log = Logger.getLogger(SingleConnectionManager.class);

	private Connection connection;
	private String dbName;
	private String finalURL;
	private String noDbURL;
	private String userName;
	private String password;
	private String dbDriver;

	private static final String DEFAULT_DB_NAME = "test_db";
	private static final String DEFAULT_USERNAME = "root";
	private static final String DEFAULT_PASSWORD = "qqqq";
	private static final String DEFAULT_FINAL_URL = "jdbc:log4jdbc:mysql://localhost:3306/" + DEFAULT_DB_NAME;
	private static final String DEFAULT_NO_DB_URL = "jdbc:log4jdbc:mysql://localhost:3306/";

	public SingleConnectionManager(String propertiesFilePath) {
		log.info(LOG_SCM_START);
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(propertiesFilePath));
			this.userName = properties.getProperty("userName");
			this.password = properties.getProperty("password");
			String urlPrefix = properties.getProperty("urlPrefix");
			String colon = properties.getProperty("colon");
			String domain = properties.getProperty("domain");
			String port = properties.getProperty("port");
			String slash = properties.getProperty("slash");
			this.dbName = properties.getProperty("dbName");
			this.dbDriver = properties.getProperty("dbDriver");

			//Final url forming: urlPrefix + domain + colon + port + slash + dbName
			this.finalURL = urlPrefix + domain + colon + port + slash + dbName;

			//no-db-url
			this.noDbURL = urlPrefix + domain + colon + port + slash;

			log.info(LOG_SCM_PROPERTIES_INIT);
		} catch (IOException e) {
			log.info(LOG_SCM_DEFAULT_DB_SETTINGS);
			log.error(e);
			this.dbName = DEFAULT_DB_NAME;
			this.finalURL = DEFAULT_FINAL_URL;
			this.noDbURL = DEFAULT_NO_DB_URL;
			this.userName = DEFAULT_USERNAME;
			this.password = DEFAULT_PASSWORD;
		}
	}

	public SingleConnectionManager(String url, String dbName, String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.finalURL = url;
		this.dbName = dbName;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			log.info("Getting new connection...");
			log.info("finalURL: " + finalURL);
			log.info("Username: " + userName);
			log.info("Password: " + "*****");

			connection = DriverManager.getConnection(noDbURL, userName, password);
			new Bootstrap(this).checkDB();
			this.close();
			connection = DriverManager.getConnection(finalURL, userName, password);
		}
		return connection;
	}

	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			log.info(LOG_SCM_CLOSE);
		}
		connection = null;
	}

	public String getDbName() {
		return dbName;
	}
}
