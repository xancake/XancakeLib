package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.intern.SqlCommand_I;

abstract class SqlCommand implements SqlCommand_I {
	protected SqlCommand_I myPredecessor;
	protected String mySQL;
	
	protected SqlCommand(String sql, SqlCommand_I predecessor) {
		mySQL = sql;
		myPredecessor = predecessor;
	}
	
	boolean hasPredecessor() {
		return myPredecessor != null;
	}
	
	SqlCommand_I getPredecessor() {
		return myPredecessor;
	}
	
	StringBuilder buildSqlCommand(StringBuilder sb) {
		if(hasPredecessor()) {
			// TODO: Unsauberen Cast irgendwie schöner umsetzen
			((SqlCommand)getPredecessor()).buildSqlCommand(sb);
			sb.append(" ");
		}
		buildOwnSql(sb);
		return sb;
	}
	
	abstract StringBuilder buildOwnSql(StringBuilder sb);
	
	@Override
	public String toString() {
		return buildOwnSql(new StringBuilder("[SQL] ")).toString();
	}
}
