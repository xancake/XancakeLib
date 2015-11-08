package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBInsertStatement extends AbstractDBStatement {
	private static final String SQL_INSERT = "INSERT INTO {0} (id, {1}) VALUES (?, {2})";
	
	public DBInsertStatement(String table, String... fields) {
		this(table, DBStatements.concatenateWithComma(fields), buildWildcardsString(fields));
	}
	
	private DBInsertStatement(String table, String fields, String wildcards) {
	    super(MessageFormat.format(SQL_INSERT, table, fields, wildcards));
    }
	
	private static String buildWildcardsString(String... fields) {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<fields.length; i++) {
			builder.append("?");
			if(i != fields.length-1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
}
