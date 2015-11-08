package de.xancake.io.persistence.broker.db.statement;

import java.text.MessageFormat;

public class DBAqcuireIDStatement extends AbstractDBStatement {
	private static final String SQL_SELECT_NEW_ID  = "SELECT max(id)+1 FROM {0}";
	
	public DBAqcuireIDStatement(String table) {
	    super(MessageFormat.format(SQL_SELECT_NEW_ID, table));
    }
}
