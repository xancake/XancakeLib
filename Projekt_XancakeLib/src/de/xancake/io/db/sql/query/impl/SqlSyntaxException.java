package de.xancake.io.db.sql.query.impl;

@SuppressWarnings("serial")
public class SqlSyntaxException extends RuntimeException {
	public SqlSyntaxException() {
		super();
	}
	
	public SqlSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SqlSyntaxException(String message) {
		super(message);
	}
	
	public SqlSyntaxException(Throwable cause) {
		super(cause);
	}
}
