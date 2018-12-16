package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.Join;
import de.xancake.io.db.sql.query.impl.JoinTypeImpl;

public interface Joinable extends Orderable {
	
	Join join(JoinTypeImpl type, String table);
	
	Join join(JoinTypeImpl type, String table, String alias);
}
