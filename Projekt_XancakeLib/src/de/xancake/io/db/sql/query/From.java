package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.Groupable;
import de.xancake.io.db.sql.query.intern.Joinable;
import de.xancake.io.db.sql.query.intern.Orderable;
import de.xancake.io.db.sql.query.intern.SqlCommand;
import de.xancake.io.db.sql.query.intern.Stateable;
import de.xancake.io.db.sql.query.intern.Whereable;

public interface From extends SqlCommand, Stateable, Joinable, Whereable, Orderable, Groupable {
	
}
