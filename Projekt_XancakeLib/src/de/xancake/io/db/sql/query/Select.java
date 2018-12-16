package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.SqlCommand;

public interface Select extends SqlCommand {
	
	From from(String table);
	
	From from(String table, String alias);
}
