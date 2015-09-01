package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.SqlCommand_I;

public interface Select_I extends SqlCommand_I {
	
	From_I from(String table);
	
	From_I from(String table, String alias);
}
