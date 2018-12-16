package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.SqlCommand;
import de.xancake.io.db.sql.query.intern.Stateable;

public interface WhereAnd extends SqlCommand, Stateable {
	
	WhereAnd and(String condition);
	
	WhereOr or(String condition);
}
