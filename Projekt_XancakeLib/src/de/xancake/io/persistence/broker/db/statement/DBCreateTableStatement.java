package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBCreateTableStatement extends AbstractDBStatement {
	private static final String SQL_CREATE = "CREATE TABLE {0} (id INTEGER NOT NULL, {1}, PRIMARY KEY (id))";
	
	public DBCreateTableStatement(String table, String... attributeClauses) {
		this(table, DBStatements.concatenateWithComma(attributeClauses));
	}
	
	public DBCreateTableStatement(String table, String attributeClause) {
	    super(MessageFormat.format(SQL_CREATE, table, attributeClause));
    }
}
