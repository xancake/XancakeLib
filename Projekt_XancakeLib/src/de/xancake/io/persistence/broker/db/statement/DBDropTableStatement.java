package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBDropTableStatement extends AbstractDBStatement {
	private static final String SQL_DROP_TABLE   = "DROP TABLE {0}";
	
	public DBDropTableStatement(String table) {
	    super(MessageFormat.format(SQL_DROP_TABLE, table));
    }
}
