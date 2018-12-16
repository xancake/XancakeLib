package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.SqlCommand;

public interface InsertInto extends SqlCommand {
	
	Values values(Object... attributeValues);
	
	/**
	 * Initializes the VALUES-Part of the SQL-Statement with the appropriate number of placeholders ({@code ?}) by retrieving the count of the attribute names.
	 */
	Values valuesWildcard();
}
