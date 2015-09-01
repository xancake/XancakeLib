package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.JoinOn_I;
import de.xancake.io.db.sql.query.Join_I;
import de.xancake.io.db.sql.query.Where_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

class Join extends Stateable implements Join_I {
	private JoinType myType;
	private String myTable;
	private String myTableAlias;
	
	
	protected Join(SqlCommand_I predecessor, JoinType type, String table) {
		this(predecessor, type, table, null);
	}
	
	protected Join(SqlCommand_I predecessor, JoinType type, String table, String alias) {
		super(SqlConstants_I.JOIN, predecessor);
		myType = type;
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
	public JoinOn_I on(String leftKey, String rightKey) {
		return new JoinOn(this, leftKey, rightKey);
	}
	
	@Override
	public Where_I where(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(myType.getTypeString());
		sb.append(" ");
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
