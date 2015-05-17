package org.mydomain.academy.db.utils;

public enum LogMessages {

	LOG_CONSOLE_INIT("Console interface has been initialized"),
	LOG_CONSOLE_INTERFACE_INIT("Console interface has been started"),
	LOG_CONSOLE_INTERFACE_FINISH("Console interface has been finished."),
	LOG_SCM_DEFAULT_DB_SETTINGS("Can't find property-file. Loading default jdbc settings."),
	LOG_SCM_PROPERTIES_INIT("Settings were successfully initialized from the property-file."),
	LOG_SCM_START("SingleConnectionManager has been started."),
	LOG_SCM_CLOSE("Connection was closed."),
	LOG_MAIN_START("Application has been started."),
	LOG_MAIN_CON_ERROR("Connection error: "),
	LOG_MAIN_FINISH_SUCCESS("Application has been finished successfully."),
	LOG_MAIN_FINISH_ERROR("Application has been finished with an error."),
	LOG_MAIN_FINISH_DIVIDER("___________________________________________\n\n"),
	LOG_BOOTSTRAP_INIT("Bootstrap has been initialized."),
	LOG_BOOTSTRAP_START("Bootstrap.init() has been started."),
	LOG_BOOTSTRAP_FINISH("Bootstrap.init() has been finished."),

	LOG_FORMATTER_STRING_TO_DATE("Parsing from string to date: "),
	LOG_FORMATTER_STRING_TO_DATE_RESULT("Result from parsing string to date: "),

	LOG_NOT_IMPLEMENTED_IN_HIBERNATE("This method is not implemented in Hibernate. It was reserved for using with JDBC."),
	LOG_NOT_IMPLEMENTED_IN_JDBC("This method is not implemented in JDBC. It was reserved for using with Hibernate."),
	LOG_NOT_IMPLEMENTED_IN_JPA("This method is not implemented in JPA."),

	;

	private String message;

	LogMessages(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
