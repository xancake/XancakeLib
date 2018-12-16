package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.GroupBy;

public interface Groupable {
	
	GroupBy groupBy(String... criteria);
}
