package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBUpdateStatement extends AbstractDBStatement {
	private static final String SQL_UPDATE = "UPDATE {0} SET {1} WHERE id=?";
	
	public DBUpdateStatement(String table, String... fields) {
		this(table, buildFieldsString(fields));
	}
	
	private DBUpdateStatement(String table, String fields) {
	    super(MessageFormat.format(SQL_UPDATE, table, fields));
    }
	
	private static String buildFieldsString(String... fields) {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<fields.length; i++) {
			builder.append(fields[i]);
			builder.append("=?");
			if(i != fields.length-1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
}
