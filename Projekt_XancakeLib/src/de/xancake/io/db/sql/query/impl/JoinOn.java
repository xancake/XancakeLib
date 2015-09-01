package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.JoinOn_I;
import de.xancake.io.db.sql.query.Join_I;
import de.xancake.io.db.sql.query.Where_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

class JoinOn extends Stateable implements JoinOn_I {
	private String myLeftSide;
	private String myRightSide;
	
	protected JoinOn(SqlCommand_I predecessor, String leftSide, String rightSide) {
		super(SqlConstants_I.JOIN_ON, predecessor);
		myLeftSide = leftSide;
		myRightSide = rightSide;
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
		sb.append(myLeftSide);
		sb.append("=");
		sb.append(myRightSide);
		return sb;
	}
}
