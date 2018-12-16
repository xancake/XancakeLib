package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.Groupable;
import de.xancake.io.db.sql.query.intern.Orderable;
import de.xancake.io.db.sql.query.intern.SqlCommand;
import de.xancake.io.db.sql.query.intern.Stateable;

public interface Where extends SqlCommand, Stateable, Orderable, Groupable {
	
	WhereAnd and(String condition);
	
	
	WhereOr or(String condition);
}
