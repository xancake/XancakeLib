package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.From;
import de.xancake.io.db.sql.query.InsertInto;
import de.xancake.io.db.sql.query.Select;
import de.xancake.io.db.sql.query.intern.Stateable;

public final class SQL {
	
	public static Select select() {
		return new SelectImpl();
	}
	
	public static Select select(String... attributes) {
		return new SelectImpl(attributes);
	}
	
	public static From selectFrom(String table) {
		return new FromImpl(new SelectImpl(), table);
	}
	
	public static From selectFrom(String table, String alias) {
		return new FromImpl(new SelectImpl(), table, alias);
	}
	
	public static InsertInto insertInto(String table) {
		return new InsertIntoImpl(table);
	}
	
	public static InsertInto insertInto(String table, String... attributeNames) {
		return new InsertIntoImpl(table, attributeNames);
	}
	
	/**
	 * Creates a {@link Stateable_I stateable} from a raw SQL-Statement.
	 * This only makes sense, if the {@link Stateable_I#execute()}- or {@link Stateable_I#prepare()}-Method is going to be used.
	 * @param statement The raw SQL-Statement
	 * @return A {@link Stateable_I}
	 * @deprecated Only to be used, if a statement absolutely cannot be created with this API.
	 */
	@Deprecated
	public static Stateable custom(String statement) {
		return new StateableImpl(statement, null) {
			@Override
			StringBuilder buildOwnSql(StringBuilder sb) {
				return sb.append(_sql);
			}
		};
	}
}
