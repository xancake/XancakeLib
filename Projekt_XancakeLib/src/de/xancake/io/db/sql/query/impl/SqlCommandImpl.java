package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.intern.SqlCommand;

abstract class SqlCommandImpl implements SqlCommand {
	protected SqlCommand _predecessor;
	protected String _sql;
	
	protected SqlCommandImpl(String sql, SqlCommand predecessor) {
		_sql = sql;
		_predecessor = predecessor;
	}
	
	boolean hasPredecessor() {
		return _predecessor != null;
	}
	
	SqlCommand getPredecessor() {
		return _predecessor;
	}
	
	StringBuilder buildSqlCommand(StringBuilder sb) {
		if(hasPredecessor()) {
			// TODO: Unsauberen Cast irgendwie schöner umsetzen
			((SqlCommandImpl)getPredecessor()).buildSqlCommand(sb);
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
