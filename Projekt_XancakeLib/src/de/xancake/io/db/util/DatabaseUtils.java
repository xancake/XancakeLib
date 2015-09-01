package de.xancake.io.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
	private DatabaseUtils() {}
	
	public static void close(Connection connection) throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	
	public static void close(Statement statement) throws SQLException {
		if(statement != null) {
			statement.close();
		}
	}
	
	public static void close(ResultSet resultSet) throws SQLException {
		if(resultSet != null) {
			resultSet.close();
		}
	}
	
	public static void close(Statement statement, ResultSet resultSet) throws SQLException {
		close(statement);
		close(resultSet);
	}
}
