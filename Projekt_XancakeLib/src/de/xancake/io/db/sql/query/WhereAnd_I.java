package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.SqlCommand_I;
import de.xancake.io.db.sql.query.intern.Stateable_I;

public interface WhereAnd_I extends SqlCommand_I, Stateable_I {
	
	WhereAnd_I and(String condition);
	
	WhereOr_I or(String condition);
}
