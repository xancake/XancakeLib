package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBDeleteStatement extends AbstractDBStatement {
	private static final String SQL_DELETE = "DELETE FROM {0} WHERE id=?";
	
	public DBDeleteStatement(String table) {
	    super(MessageFormat.format(SQL_DELETE, table));
    }
}
