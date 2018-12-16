package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.OrderBy;
import de.xancake.io.db.sql.query.OrderBy.Order;

public interface Orderable {
	
	OrderBy orderBy(String field);
	
	
	OrderBy orderBy(String field, Order order);
}
