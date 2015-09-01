package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.Joinable_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;
import de.xancake.io.db.sql.query.intern.Stateable_I;
import de.xancake.io.db.sql.query.intern.Whereable_I;

public interface Join_I extends SqlCommand_I, Stateable_I, Joinable_I, Whereable_I {
	
	JoinOn_I on(String leftKey, String rightKey);
}