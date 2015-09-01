package de.xancake.io.db.sql.query.intern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Stateable_I {
	
	/**
	 * Returns the SQL represented by this Statement.
	 * @return The SQL as String
	 */
	String toSql();
	
	/**
	 * Creates a {@link PreparedStatement} ready to execute using the JDBC-{@link Connection}.
	 * @param connection The database {@link Connection}
	 * @return A {@link PreparedStatement}
	 */
	PreparedStatement prepare(Connection connection) throws SQLException;
}
