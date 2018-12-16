package de.xancake.io.db.sql.query.intern;

import de.xancake.io.db.sql.query.Where;

public interface Whereable {
	
	Where where(String condition);
}
