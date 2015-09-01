package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.From_I;
import de.xancake.io.db.sql.query.Join_I;
import de.xancake.io.db.sql.query.Where_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

class From extends Stateable implements From_I {
	private String myTable;
	private String myTableAlias;
	
	protected From(SqlCommand_I predecessor, String table) {
		this(predecessor, table, null);
	}
	
	protected From(SqlCommand_I predecessor, String table, String alias) {
		super(SqlConstants_I.FROM, predecessor);
		myTable = table;
		myTableAlias = alias;
	}
	
	@Override
	public Join_I join(JoinType type, String table) {
		return new Join(this, type, table);
	}
	
	@Override
	public Join_I join(JoinType type, String table, String alias) {
		return new Join(this, type, table, alias);
	}
	
	@Override
	public Where_I where(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(mySQL);
		sb.append(" ");
		sb.append(myTable);
		if(myTableAlias != null) {
			sb.append(" ");
			sb.append(SqlConstants_I.FROM_AS);
			sb.append(" ");
			sb.append(myTableAlias);
		}
		return sb;
	}
}
