package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.WhereAnd_I;
import de.xancake.io.db.sql.query.WhereOr_I;
import de.xancake.io.db.sql.query.Where_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

class Where extends Stateable implements Where_I {
	
	
	
	@Override
	public WhereAnd_I and(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WhereOr_I or(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Where(SqlCommand_I predecessor) {
		super(SqlConstants_I.WHERE, predecessor);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		return null;
	}
}
