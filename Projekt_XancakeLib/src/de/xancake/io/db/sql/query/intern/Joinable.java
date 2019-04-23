package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.Join;
import de.xancake.io.db.sql.query.impl.JoinType;

public interface Joinable extends Orderable {
	
	Join join(JoinType type, String table);
	
	Join join(JoinType type, String table, String alias);
}
