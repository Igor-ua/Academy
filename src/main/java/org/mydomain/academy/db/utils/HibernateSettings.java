package org.mydomain.academy.db.utils;

import org.hibernate.cfg.Configuration;
import org.mydomain.academy.db.entities.*;

public class HibernateSettings {

	private StringBuilder builder;

	public HibernateSettings() {
		//example: jdbc:log4jdbc:h2:tcp://domain:port/db_name
		this.builder = new StringBuilder();
		this.builder.append(URL_PREFIX).append(DOMAIN).append(COLON).append(PORT).append(SLASH).append(DB_NAME);
		this.hibernateConnectionURL = builder.toString();
	}

	private static final String URL_PREFIX = "jdbc:log4jdbc:h2:tcp://";
	private static final String DOMAIN = "localhost";
	private static final String COLON = ":";
	private static final String PORT = "64000";
	private static final String SLASH = "/";
	private static final String DB_NAME = "H2_Hibernate";

	private String hibernateConnectionURL;

	private static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	private static final String HIBERNATE_CONNECTION_USERNAME = "sa";
	private static final String HIBERNATE_CONNECTION_PASSWORD = "";
	private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String HIBERNATE_HBM_2_DDL_AUTO = "update";
	private static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "thread";
	private static final String HIBERNATE_SHOW_SQL = "true";

	public void configure(Configuration configuration) {
		configuration.setProperty("hibernate.connection.url", hibernateConnectionURL);
		configuration.setProperty("hibernate.connection.driver_class", HIBERNATE_CONNECTION_DRIVER_CLASS);
		configuration.setProperty("hibernate.connection.username", HIBERNATE_CONNECTION_USERNAME);
		configuration.setProperty("hibernate.connection.password", HIBERNATE_CONNECTION_PASSWORD);
		configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		configuration.setProperty("show_sql", HIBERNATE_SHOW_SQL);
		configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM_2_DDL_AUTO);
		configuration.setProperty("hibernate.current_session_context_class", HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS);
	}

	public void addMappings(Configuration configuration) {
		configuration.addAnnotatedClass(Form.class);
		configuration.addAnnotatedClass(Group.class);
		configuration.addAnnotatedClass(Mark.class);
		configuration.addAnnotatedClass(MarkType.class);
		configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(Schedule.class);
		configuration.addAnnotatedClass(Specialization.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Teacher.class);
	}

	public static String getPort() {
		return PORT;
	}
}
