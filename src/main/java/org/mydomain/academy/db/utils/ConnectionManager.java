package org.mydomain.academy.db.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
	Connection getConnection() throws SQLException;

	public void close() throws SQLException;
}
