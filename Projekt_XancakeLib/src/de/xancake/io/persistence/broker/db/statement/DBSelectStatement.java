package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBSelectStatement extends AbstractDBStatement {
	private static final String SQL_SELECT = "SELECT * FROM {0} WHERE id=?";
	
	public DBSelectStatement(String table) {
	    super(MessageFormat.format(SQL_SELECT, table));
    }
}
