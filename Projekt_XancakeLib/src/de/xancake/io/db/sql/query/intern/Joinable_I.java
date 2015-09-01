package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.Join_I;
import de.xancake.io.db.sql.query.impl.JoinType;

public interface Joinable_I {
	
	Join_I join(JoinType type, String table);
	
	Join_I join(JoinType type, String table, String alias);
}
