package de.xancake.io.db.sql.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.xancake.io.db.sql.query.intern.SqlCommand_I;
import de.xancake.io.db.sql.query.intern.Stateable_I;

abstract class Stateable extends SqlCommand implements Stateable_I {
	
	
	
	protected Stateable(String sql, SqlCommand_I predecessor) {
		super(sql, predecessor);
	}
	
	// TODO: execute(), executeUpdate(), executeQuery() implementieren
//	@Override
//	public ResultSet execute(Connection connection) throws SQLException {
//		Statement statement = connection.createStatement();
//		return statement.executeQuery(toSql());
//	}
	
	@Override
	public PreparedStatement prepare(Connection connection) throws SQLException {
		return connection.prepareStatement(toSql());
	}
	
	@Override
	public final String toSql() {
		return buildSqlCommand(new StringBuilder()).toString();
	}
	
	@Override
	public String toString() {
		return toSql();
	}
}
