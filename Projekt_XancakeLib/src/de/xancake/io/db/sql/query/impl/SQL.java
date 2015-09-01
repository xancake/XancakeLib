package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.From_I;
import de.xancake.io.db.sql.query.InsertInto_I;
import de.xancake.io.db.sql.query.Select_I;
import de.xancake.io.db.sql.query.intern.Stateable_I;

public final class SQL {
	
	public static Select_I select() {
		return new Select();
	}
	
	public static Select_I select(String... attributes) {
		return new Select(attributes);
	}
	
	public static From_I selectFrom(String table) {
		return new From(new Select(), table);
	}
	
	public static From_I selectFrom(String table, String alias) {
		return new From(new Select(), table, alias);
	}
	
	public static InsertInto_I insertInto(String table) {
		return new InsertInto(table);
	}
	
	public static InsertInto_I insertInto(String table, String... attributeNames) {
		return new InsertInto(table, attributeNames);
	}
	
	/**
	 * Creates a {@link Stateable_I stateable} from a raw SQL-Statement.
	 * This only makes sense, if the {@link Stateable_I#execute()}- or {@link Stateable_I#prepare()}-Method is going to be used.
	 * @param statement The raw SQL-Statement
	 * @return A {@link Stateable_I}
	 * @deprecated Only to be used, if a statement absolutely cannot be created with this API.
	 */
	@Deprecated
	public static Stateable_I custom(String statement) {
		return new Stateable(statement, null) {
			@Override
			StringBuilder buildOwnSql(StringBuilder sb) {
				return sb.append(mySQL);
			}
		};
	}
}
